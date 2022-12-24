package com.example.shop.controller.dto.request;

import com.example.shop.model.SalePair;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class SaleRegistrationRequest {
	@NotBlank
	private List<SalePair> products;
	@JsonProperty("final_price_kopecks")
	@NotNull
	private Long finalPriceKopecks;
}
