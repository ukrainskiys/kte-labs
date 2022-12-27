package com.example.shop.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class ProductEvaluationRequest {
	@JsonProperty("client_id")
	@NotNull
	private Long clientId;
	@JsonProperty("product_id")
	@NotNull
	private Long productId;
	@Min(1)
	@Max(5)
	private Integer rating;
}
