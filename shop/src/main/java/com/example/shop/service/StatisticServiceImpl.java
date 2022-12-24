package com.example.shop.service;

import com.example.shop.model.entity.Statistic;
import com.example.shop.repository.StatisticsRepository;
import com.example.shop.controller.dto.request.GetStatisticsRequest;
import com.example.shop.controller.dto.response.GetStatisticsResponse;
import com.example.shop.service.errors.ClientNotFoundException;
import com.example.shop.service.errors.IncorrectGetStatisticResponseException;
import com.example.shop.service.errors.ProductNotFoundException;
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
		Statistic statistic = statisticsRepository.findByClientId(clientId)
			.orElseThrow(() -> new ClientNotFoundException(clientId));
		return buildStatisticResponse(statistic);
	}

	@Override
	public GetStatisticsResponse getProductStatistic(long productId) {
		Statistic statistic = statisticsRepository.findByProductId(productId)
			.orElseThrow(() -> new ProductNotFoundException(productId));
		return buildStatisticResponse(statistic);
	}

	private GetStatisticsResponse buildStatisticResponse(Statistic statistic) {
		return GetStatisticsResponse.builder()
			.countChecks(statistic.getCountChecks())
			.totalCost(statistic.getTotalCostsKopecks())
			.discountAmount(statistic.getTotalDiscountsKopecks())
			.build();
	}
}
