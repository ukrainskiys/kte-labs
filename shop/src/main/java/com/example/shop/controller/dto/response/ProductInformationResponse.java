package com.example.shop.controller.dto.response;

import com.example.shop.model.RatingPair;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
public class ProductInformationResponse extends AbstractResponse {
	private String description;
	@JsonProperty("average_rating")
	private BigDecimal averageRating;
	private List<RatingPair> ratings;
	@JsonProperty("current_client_rating")
	private Integer currentClientRating;
}
