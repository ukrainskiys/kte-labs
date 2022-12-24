package com.example.shop.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SetIndividualDiscountsRequest {
	private long id;
	private int discountFirst;
	private int discountSecond;
}
