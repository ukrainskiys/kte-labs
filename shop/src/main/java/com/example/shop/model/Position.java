package com.example.shop.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Position {
	@JsonProperty("product_id")
	private long productId;
	private int count;
	private long price;
	@JsonProperty("final_price")
	private long finalPrice;
	@JsonProperty("final_discount")
	private int finalDiscount;
}
