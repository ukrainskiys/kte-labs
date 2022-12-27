package com.example.shop.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class GetStatisticsResponse {
	@JsonProperty("count_checks")
	private Integer countChecks;
	@JsonProperty("total_cost")
	private BigDecimal totalCost;
	@JsonProperty("discount_amount")
	private BigDecimal discountAmount;
}
