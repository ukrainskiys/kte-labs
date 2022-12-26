package com.example.shop.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class GetStatisticsResponse {
	@JsonProperty("count_checks")
	private Integer countChecks;
	@JsonProperty("total_cost")
	private BigDecimal totalCost;
	@JsonProperty("discount_amount")
	private BigDecimal discountAmount;
}
