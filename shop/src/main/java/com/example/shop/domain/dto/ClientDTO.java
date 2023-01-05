package com.example.shop.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ClientDTO {
    private Long id;
    private String name;
    @JsonProperty("individual_discount_first")
    private Integer individualDiscountFirst;
    @JsonProperty("individual_discount_second")
    private Integer individualDiscountSecond;
}
