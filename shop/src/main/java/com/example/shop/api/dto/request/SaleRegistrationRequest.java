package com.example.shop.api.dto.request;

import com.example.shop.model.SalePair;
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
	private List<SalePair> products;
	@JsonProperty("final_price_kopecks")
	@NotNull
	private Long finalPriceKopecks;
}
