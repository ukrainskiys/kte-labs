package com.example.shop.controller.dto.request;

import com.example.shop.model.SalePair;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SaleRegistrationRequest {
	private List<SalePair> products;
	@JsonProperty("final_price_kopecks")
	private long finalPriceKopecks;
}
