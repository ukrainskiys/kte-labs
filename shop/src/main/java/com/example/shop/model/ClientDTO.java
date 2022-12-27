package com.example.shop.model;

import com.example.shop.model.entity.Client;
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

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.individualDiscountFirst = client.getIndividualDiscountFirst();
        this.individualDiscountSecond = client.getIndividualDiscountSecond();
    }
}
