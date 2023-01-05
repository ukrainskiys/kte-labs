package com.example.shop.controllers.request;

import com.example.shop.domain.ProductCountPair;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
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
