package com.example.shop.service.calculate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CalculationResult {
	private final int discount;
	private final long sumKopecks;
	private final long discountSumKopecks;
}
