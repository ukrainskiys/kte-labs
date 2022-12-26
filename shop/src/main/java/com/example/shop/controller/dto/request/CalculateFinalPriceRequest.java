package com.example.shop.controller.dto.request;

import com.example.shop.model.SalePair;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class CalculateFinalPriceRequest {
	@JsonProperty("client_id")
	@NotNull
	private Long clientId;
	@NotNull
	private List<SalePair> products = new ArrayList<>();
}
