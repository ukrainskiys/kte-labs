package com.example.shop.service;

import com.example.shop.controllers.request.GetStatisticsRequest;
import com.example.shop.controllers.response.GetStatisticsResponse;

public interface StatisticService {
	GetStatisticsResponse getStatistic(GetStatisticsRequest request);
	GetStatisticsResponse getClientStatistic(long clientId);
	GetStatisticsResponse getProductStatistic(long productId);
}
