package com.example.shop.controller.dto.response;

import com.example.shop.model.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetAllProductsResponse {
    private final List<ProductDTO> products;
}
