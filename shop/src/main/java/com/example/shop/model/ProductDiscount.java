package com.example.shop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductDiscount {
	private Long productId;
	private Integer percentDiscount;
}
