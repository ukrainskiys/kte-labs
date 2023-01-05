package com.example.shop.service.schedule.statistic;

import com.example.shop.domain.model.Statistic;
import com.example.shop.repository.StatisticsRepository;
import com.example.shop.util.MoneyUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductStatisticRowHandler implements StatisticRowHandler {
    private final StatisticsRepository statisticsRepository;

    @Override
    public void handle(List<StatisticRow> statisticRows, LocalDateTime now) {
        Map<Long, List<StatisticRow>> map = statisticRows.stream()
                .filter(row -> row.getProductId() != null)
                .collect(Collectors.groupingBy(StatisticRow::getProductId));

        map.forEach((productId, list) -> {
            int countChecks = list.stream().map(StatisticRow::getCheck).collect(Collectors.toSet()).size();
            BigDecimal totalPrice = BigDecimal.ZERO;
            BigDecimal totalDiscount = BigDecimal.ZERO;
            for (StatisticRow row : list) {
                countChecks++;
                totalPrice = totalPrice.add(row.getFinalPrice());
                totalDiscount = MoneyUtils.ofPercent(totalPrice, row.getFinalDiscount() + 100).subtract(totalPrice);
            }

            Statistic stat = statisticsRepository.findByProductId(productId).orElse(null);
            if (stat != null) {
                stat.setCountChecks(stat.getCountChecks() + countChecks);
                stat.setTotalCosts(stat.getTotalCosts().add(totalPrice));
                stat.setTotalDiscounts(stat.getTotalDiscounts().add(totalDiscount));
            } else {
                stat = new Statistic();
                stat.setProductId(productId);
                stat.setCountChecks(countChecks);
                stat.setTotalCosts(totalPrice);
                stat.setTotalDiscounts(totalDiscount);
            }
            stat.setLastUpdate(now);
            statisticsRepository.save(stat);
        });
    }
}
