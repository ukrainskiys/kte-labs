package com.example.shop.model.entity;

import com.example.shop.model.Position;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "sale_facts")
@NoArgsConstructor
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
	@JdbcTypeCode(SqlTypes.JSON)
	private List<Position> positions;
}
