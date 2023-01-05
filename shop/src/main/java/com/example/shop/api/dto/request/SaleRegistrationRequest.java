package com.example.shop.api.dto.request;

import com.example.shop.domain.ProductCountPair;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class SaleRegistrationRequest {
	@NotNull
	private List<ProductCountPair> products;
	@JsonProperty("final_price_kopecks")
	@NotNull
	private Long finalPriceKopecks;
}
