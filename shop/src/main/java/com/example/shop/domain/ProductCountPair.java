package com.example.shop.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCountPair {
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
		ProductCountPair salePair = (ProductCountPair) o;
		return productId.equals(salePair.productId) && count.equals(salePair.count);
	}

	@Override
	public int hashCode() {
		return Objects.hash(productId, count);
	}
}
