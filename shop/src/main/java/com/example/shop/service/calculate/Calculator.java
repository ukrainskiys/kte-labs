package com.example.shop.service.calculate;

import com.example.shop.domain.ProductCountPair;
import com.example.shop.domain.model.Client;

import java.util.List;

public interface Calculator {
	List<CalculationResult> calculate(Client client, List<ProductCountPair> sales);
}
