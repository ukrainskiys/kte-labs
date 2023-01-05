package com.example.shop.service;

import com.example.shop.controllers.request.SaleStatisticsRequest;
import com.example.shop.controllers.response.SaleStatisticsResponse;

public interface StatisticService {
	SaleStatisticsResponse getStatistic(SaleStatisticsRequest request);
	SaleStatisticsResponse getClientStatistic(long clientId);
	SaleStatisticsResponse getProductStatistic(long productId);
}
