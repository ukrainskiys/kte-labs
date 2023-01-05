package com.example.shop.controllers.request;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class GetStatisticsRequest {
    @JsonProperty("client_id")
    private Long clientId;
    @JsonProperty("product_id")
    private Long productId;
}
