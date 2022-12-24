package com.example.shop.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product_discount")
public class ProductDiscount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	@OneToOne(fetch = FetchType.LAZY)
	private Product product;
	@Column(name = "percent_discount")
	private Integer percentDiscount;
}
