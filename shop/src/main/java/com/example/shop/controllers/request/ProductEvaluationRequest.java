package com.example.shop.controllers.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
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
