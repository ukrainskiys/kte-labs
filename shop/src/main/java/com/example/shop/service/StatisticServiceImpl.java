package com.example.shop.service;

import com.example.shop.domain.model.Statistic;
import com.example.shop.repository.StatisticsRepository;
import com.example.shop.controllers.request.SaleStatisticsRequest;
import com.example.shop.controllers.response.SaleStatisticsResponse;
import com.example.shop.service.errors.IncorrectGetStatisticResponseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {
	private final StatisticsRepository statisticsRepository;

	@Override
	public SaleStatisticsResponse getStatistic(SaleStatisticsRequest request) {
		if (request.getClientId() != null) {
			return getClientStatistic(request.getClientId());
		} else if (request.getProductId() != null) {
			return getProductStatistic(request.getProductId());
		} else {
			throw new IncorrectGetStatisticResponseException();
		}
	}

	@Override
	public SaleStatisticsResponse getClientStatistic(long clientId) {
		Statistic statistic = statisticsRepository.findByClientId(clientId).orElseGet(Statistic::new);
		return buildStatisticResponse(statistic);
	}

	@Override
	public SaleStatisticsResponse getProductStatistic(long productId) {
		Statistic statistic = statisticsRepository.findByProductId(productId).orElseGet(Statistic::new);
		return buildStatisticResponse(statistic);
	}

	private SaleStatisticsResponse buildStatisticResponse(Statistic statistic) {
		return new SaleStatisticsResponse(statistic.getCountChecks(), statistic.getTotalCosts(), statistic.getTotalDiscounts());
	}
}
