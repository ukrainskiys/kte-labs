package com.example.shop.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "sale_facts")
public class SaleFact {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	private LocalDateTime saleDate;
	private String checkNumber;
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "sale_fact_id")
	private List<SaleFactPosition> positions;
}
