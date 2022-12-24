package com.example.shop.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetStatisticsRequest {
	@JsonProperty("client_id")
	private Long clientId;
	@JsonProperty("product_id")
	private Long productId;
}
