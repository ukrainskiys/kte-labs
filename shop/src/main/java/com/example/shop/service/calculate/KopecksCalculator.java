package com.example.shop.service.calculate;

import com.example.shop.model.SalePair;
import com.example.shop.model.entity.Client;

import java.math.BigDecimal;
import java.util.List;

public interface KopecksCalculator {
	List<CalculationResult> calculate(Client client, List<SalePair> sales);
}
