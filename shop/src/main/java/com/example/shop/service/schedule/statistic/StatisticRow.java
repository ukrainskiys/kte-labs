package com.example.shop.service.schedule.statistic;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
class StatisticRow {
	private long clientId;
	private long productId;
	private LocalDateTime date;
	private String check;
	private BigDecimal finalPrice;
	private int finalDiscount;
}
