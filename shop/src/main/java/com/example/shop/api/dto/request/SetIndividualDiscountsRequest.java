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
public class SetIndividualDiscountsRequest {
	@NotNull
	private Long id;
	@JsonProperty("discount_first")
	@Min(1)
	@Max(100)
	private Integer discountFirst;
	@JsonProperty("discount_second")
	@Min(1)
	@Max(100)
	private Integer discountSecond;
}
