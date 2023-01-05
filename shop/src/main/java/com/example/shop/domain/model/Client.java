package com.example.shop.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "clients")
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	private String name;
	@JsonProperty("individual_discount_first")
	private Integer individualDiscountFirst;
	@JsonProperty("individual_discount_second")
	private Integer individualDiscountSecond;
}
