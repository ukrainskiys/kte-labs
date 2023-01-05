package com.example.shop.controllers.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class SaleStatisticsResponse {
	@JsonProperty("count_checks")
	private Integer countChecks;
	@JsonProperty("total_cost")
	private BigDecimal totalCost;
	@JsonProperty("discount_amount")
	private BigDecimal discountAmount;
}
