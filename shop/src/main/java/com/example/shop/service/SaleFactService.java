package com.example.shop.service;

import com.example.shop.model.SalePair;
import com.example.shop.model.entity.Client;
import com.example.shop.api.dto.response.SaleRegistrationResponse;
import com.example.shop.service.calculate.CalculationResult;

import java.util.List;

public interface SaleFactService {
	void keepFinalCost(Client client, List<SalePair> sales, List<CalculationResult> calculations);
	SaleRegistrationResponse saleRegistration(List<SalePair> products, long finalPriceKopecks);
}
