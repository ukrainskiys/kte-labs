package com.example.shop.controller.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ProductEvaluationRequest extends AbstractClientProductRequest {
	@Min(1)
	@Max(5)
	private Integer rating;
}
