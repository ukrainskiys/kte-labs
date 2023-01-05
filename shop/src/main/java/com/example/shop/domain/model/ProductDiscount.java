package com.example.shop.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductDiscount {
	private Long productId;
	private Integer percentDiscount;
}
