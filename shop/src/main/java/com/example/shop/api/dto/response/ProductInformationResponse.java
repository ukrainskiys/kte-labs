package com.example.shop.api.dto.response;

import com.example.shop.domain.RatingCountPair;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class ProductInformationResponse {
	private String name;
	@JsonProperty("average_rating")
	private BigDecimal averageRating;
	private List<RatingCountPair> ratings;
	@JsonProperty("current_client_rating")
	private Integer currentClientRating;
}
