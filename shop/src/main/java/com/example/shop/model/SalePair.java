package com.example.shop.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalePair {
	@JsonProperty("product_id")
	@NotNull
	private Long productId;
	@NotNull
	@Min(1)
	private Integer count;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SalePair salePair = (SalePair) o;
		return productId.equals(salePair.productId) && count.equals(salePair.count);
	}

	@Override
	public int hashCode() {
		return Objects.hash(productId, count);
	}
}
