package com.example.shop.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductInformationRequest {
	private long productId;
	private long clientId;
}
