package com.example.shop.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SalePair {
	@JsonProperty("product_id")
	private long productId;
	private int count;
}
