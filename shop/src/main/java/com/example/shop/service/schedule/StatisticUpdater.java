package com.example.shop.service.schedule;

import com.example.shop.model.Position;
import com.example.shop.model.entity.SaleFact;
import com.example.shop.model.entity.Statistic;
import com.example.shop.repository.ClientRepository;
import com.example.shop.repository.ProductRepository;
import com.example.shop.repository.SaleFactRepository;
import com.example.shop.repository.StatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StatisticUpdater implements Updater {
	private final StatisticsRepository statisticsRepository;
	private final ClientRepository clientRepository;
	private final ProductRepository productRepository;
	private final SaleFactRepository saleFactRepository;

	@Override
	@Scheduled(cron = "0 0 * * * *")
	public void refresh() {
		updateClientStatistic();
		updateProductStatistic();
	}

	private void updateClientStatistic() {
		List<Long> allIds = clientRepository.getAllIds();
		for (long id : allIds) {
			Statistic statistic = statisticsRepository.findByClientId(id)
				.orElseGet(() -> {
					Statistic s = new Statistic();
					s.setDateUpdate(LocalDateTime.MIN);
					s.setClientId(id);
					return s;
				});

			List<SaleFact> facts = saleFactRepository
				.getAllByClientIdAndCheckNumberNotNullAndSaleDateAfter(id, statistic.getDateUpdate());

			long totalCost = 0;
			long totalDiscount = 0;
			for (SaleFact fact : facts) {
				for (Position position : fact.getPositions()) {
					totalCost += position.getFinalPrice();
					totalDiscount += position.getFinalDiscount();
				}
			}

			save(statistic, totalCost, totalDiscount, facts.size());
		}
	}

	private void updateProductStatistic() {
		List<Long> allIds = productRepository.getAllIds();
		for (long id : allIds) {
			Statistic statistic = statisticsRepository.findByProductId(id)
				.orElseGet(() -> {
					Statistic s = new Statistic();
					s.setDateUpdate(LocalDateTime.MIN);
					s.setProductId(id);
					return s;
				});

			List<SaleFact> facts = saleFactRepository.getAllProductsWithoutStatistics(id, statistic.getDateUpdate());

			int checkCount = 0;
			long totalCost = 0;
			long totalDiscount = 0;
			for (SaleFact fact : facts) {
				for (Position position : fact.getPositions()) {
					if (position.getProductId() == id) {
						totalCost += position.getFinalPrice();
						totalDiscount += position.getFinalDiscount();
						checkCount++;
					}
				}
			}

			save(statistic, totalCost, totalDiscount, checkCount);
		}
	}

	private void save(Statistic statistic, long totalCost, long totalDiscount, int count) {
		statistic.setTotalCostsKopecks(statistic.getTotalCostsKopecks() + totalCost);
		statistic.setTotalDiscountsKopecks(statistic.getTotalDiscountsKopecks() + totalDiscount);
		statistic.setCountChecks(statistic.getCountChecks() + count);
		statistic.setDateUpdate(LocalDateTime.now());
		statisticsRepository.save(statistic);
	}
}
