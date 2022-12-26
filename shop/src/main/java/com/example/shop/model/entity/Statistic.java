package com.example.shop.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "statistics")
@NoArgsConstructor
public class Statistic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	private LocalDateTime lastUpdate;
	private Long clientId;
	private Long productId;
	private Integer countChecks;
	private BigDecimal totalCosts;
	private BigDecimal totalDiscounts;
}
