package com.example.shop.model;

import com.example.shop.model.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ProductDTO {
	private Long id;
	private String description;
	private BigDecimal price;

	public ProductDTO(Product product) {
		this.id = product.getId();
		this.description = product.getDescription();
		this.price = product.getPrice();
	}
}
