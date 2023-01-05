package com.example.shop.controllers;

import com.example.shop.controllers.request.ProductEvaluationRequest;
import com.example.shop.controllers.request.ProductInformationRequest;
import com.example.shop.controllers.response.GetAllProductsResponse;
import com.example.shop.controllers.response.ProductInformationResponse;

public interface ProductApi {
    GetAllProductsResponse all();
    ProductInformationResponse info(ProductInformationRequest request);
    void evaluation(ProductEvaluationRequest request);
}
