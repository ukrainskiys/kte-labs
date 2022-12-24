package com.example.shop.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetStatisticsResponse {
	@JsonProperty("count_checks")
	private int countChecks;
	@JsonProperty("total_cost")
	private long totalCost;
	@JsonProperty("discount_amount")
	private long discountAmount;
}
