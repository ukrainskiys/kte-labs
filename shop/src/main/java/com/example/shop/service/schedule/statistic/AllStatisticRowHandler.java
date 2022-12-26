package com.example.shop.service.schedule.statistic;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Primary
@Component
@RequiredArgsConstructor
public class AllStatisticRowHandler implements StatisticRowHandler {
    private final StatisticAggregator statisticAggregator;
    private final ClientStatisticRowHandler clientStatisticRowHandler;
    private final ProductStatisticRowHandler productStatisticRowHandler;

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void handle(List<StatisticRow> statisticRows, LocalDateTime now) {
        List<StatisticRow> rows = statisticAggregator.getStatisticRows();
        clientStatisticRowHandler.handle(rows, now);
        productStatisticRowHandler.handle(rows, now);
    }
}
