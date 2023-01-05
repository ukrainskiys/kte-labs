package com.example.shop.service;

import com.example.shop.domain.ProductCountPair;
import com.example.shop.domain.model.Client;
import com.example.shop.controllers.response.SaleRegistrationResponse;
import com.example.shop.service.calculate.CalculationResult;

import java.util.List;

public interface SaleFactService {
	void keepFinalCost(Client client, List<ProductCountPair> sales, List<CalculationResult> calculations);
	SaleRegistrationResponse saleRegistration(List<ProductCountPair> products, long finalPriceKopecks);
}
