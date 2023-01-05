package com.example.shop.controllers.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.xml.bind.annotation.XmlRootElement;
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
