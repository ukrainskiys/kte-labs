package com.example.shop.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SetIndividualDiscountsRequest {
	@NotNull
	private Long id;
	@JsonProperty("discount_first")
	@Min(1)
	@Max(100)
	private Integer discountFirst;
	@JsonProperty("discount_second")
	@Min(1)
	@Max(100)
	private Integer discountSecond;
}
