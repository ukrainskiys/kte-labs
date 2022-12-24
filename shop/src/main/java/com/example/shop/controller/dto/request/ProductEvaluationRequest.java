package com.example.shop.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductEvaluationRequest {
	@JsonProperty("client_id")
	private long clientId;
	@JsonProperty("product_id")
	private long productId;
	private Integer rating;
}
