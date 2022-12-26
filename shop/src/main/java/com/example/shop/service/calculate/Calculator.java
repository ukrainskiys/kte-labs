package com.example.shop.service.calculate;

import com.example.shop.model.SalePair;
import com.example.shop.model.entity.Client;

import java.util.List;

public interface Calculator {
	List<CalculationResult> calculate(Client client, List<SalePair> sales);
}
