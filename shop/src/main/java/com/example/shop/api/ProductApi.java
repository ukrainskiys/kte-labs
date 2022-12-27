package com.example.shop.api;

import com.example.shop.api.dto.request.ProductEvaluationRequest;
import com.example.shop.api.dto.request.ProductInformationRequest;
import com.example.shop.api.dto.response.GetAllProductsResponse;
import com.example.shop.api.dto.response.ProductInformationResponse;

public interface ProductApi {
    GetAllProductsResponse all();
    ProductInformationResponse info(ProductInformationRequest request);
    void evaluation(ProductEvaluationRequest request);
}
