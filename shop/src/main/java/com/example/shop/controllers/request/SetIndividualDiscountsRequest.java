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
