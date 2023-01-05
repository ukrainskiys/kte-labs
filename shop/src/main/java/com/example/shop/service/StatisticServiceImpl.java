package com.example.shop.service;

import com.example.shop.domain.model.Statistic;
import com.example.shop.repository.StatisticsRepository;
import com.example.shop.controllers.request.GetStatisticsRequest;
import com.example.shop.controllers.response.GetStatisticsResponse;
import com.example.shop.service.errors.IncorrectGetStatisticResponseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {
	private final StatisticsRepository statisticsRepository;

	@Override
	public GetStatisticsResponse getStatistic(GetStatisticsRequest request) {
		if (request.getClientId() != null) {
			return getClientStatistic(request.getClientId());
		} else if (request.getProductId() != null) {
			return getProductStatistic(request.getProductId());
		} else {
			throw new IncorrectGetStatisticResponseException();
		}
	}

	@Override
	public GetStatisticsResponse getClientStatistic(long clientId) {
		Statistic statistic = statisticsRepository.findByClientId(clientId).orElseGet(Statistic::new);
		return buildStatisticResponse(statistic);
	}

	@Override
	public GetStatisticsResponse getProductStatistic(long productId) {
		Statistic statistic = statisticsRepository.findByProductId(productId).orElseGet(Statistic::new);
		return buildStatisticResponse(statistic);
	}

	private GetStatisticsResponse buildStatisticResponse(Statistic statistic) {
		return new GetStatisticsResponse(statistic.getCountChecks(), statistic.getTotalCosts(), statistic.getTotalDiscounts());
	}
}
