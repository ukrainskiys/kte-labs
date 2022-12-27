package com.example.shop.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class CalculateFinalPriceResponse {
	@JsonProperty("price_kopecks")
	private long priceKopecks;
}
