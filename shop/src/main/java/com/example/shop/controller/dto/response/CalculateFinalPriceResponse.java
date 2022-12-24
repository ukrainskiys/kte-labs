package com.example.shop.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CalculateFinalPriceResponse {
	@JsonProperty("price_kopecks")
	private long priceKopecks;
}
