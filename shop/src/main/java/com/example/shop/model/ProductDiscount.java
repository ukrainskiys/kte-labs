package com.example.shop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductDiscount {
	private Long productId;
	private Integer percentDiscount;

	@Override
	public String toString() {
		return "ProductDiscount{" +
				"productId=" + productId +
				", percentDiscount=" + percentDiscount +
				'}';
	}
}
