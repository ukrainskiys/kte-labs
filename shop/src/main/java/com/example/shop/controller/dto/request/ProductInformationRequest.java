package com.example.shop.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductInformationRequest {
	@JsonProperty("product_id")
	@NotNull
	private Long productId;
	@JsonProperty("client_id")
	@NotNull
	private Long clientId;
}
