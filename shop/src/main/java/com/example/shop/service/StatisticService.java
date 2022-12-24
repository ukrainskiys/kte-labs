package com.example.shop.service;

import com.example.shop.controller.dto.request.GetStatisticsRequest;
import com.example.shop.controller.dto.response.GetStatisticsResponse;

public interface StatisticService {
	GetStatisticsResponse getStatistic(GetStatisticsRequest request);
	GetStatisticsResponse getClientStatistic(long clientId);
	GetStatisticsResponse getProductStatistic(long productId);
}
