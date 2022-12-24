package com.example.shop.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SaleRegistrationResponse {
	@JsonProperty("check_number")
	private String checkNumber;
}
