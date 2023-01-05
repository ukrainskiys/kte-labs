package com.example.shop.controllers.response;

import com.example.shop.domain.dto.ProductDTO;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class GetAllProductsResponse {
    private List<ProductDTO> products;
}
