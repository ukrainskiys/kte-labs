package com.example.shop.model.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	private Long clientId;
	private Long productId;
	private LocalDateTime dateUpdate;
	private Integer countChecks;
	private Long totalCostsKopecks;
	private Long totalDiscountsKopecks;
}
