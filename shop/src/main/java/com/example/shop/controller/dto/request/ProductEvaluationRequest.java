package com.example.shop.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ProductEvaluationRequest {
	@JsonProperty("client_id")
	@NotNull
	private Long clientId;
	@JsonProperty("product_id")
	@NotNull
	private Long productId;
	@NotNull
	@Min(1)
	@Max(100)
	private Integer rating;
}
