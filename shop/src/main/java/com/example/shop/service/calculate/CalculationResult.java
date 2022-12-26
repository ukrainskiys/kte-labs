package com.example.shop.service.calculate;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class CalculationResult {
	private final int discount;
	private final BigDecimal sum;
	private final BigDecimal sumWithDiscount;
}
