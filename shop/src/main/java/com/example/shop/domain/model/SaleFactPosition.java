package com.example.shop.domain.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "sale_fact_positions")
public class SaleFactPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "sale_fact_id")
    private SaleFact saleFact;
    private Long productId;
    private Integer count;
    private BigDecimal price;
    private BigDecimal finalPrice;
    private Integer finalDiscountPercent;
}
