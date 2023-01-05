package com.example.shop.controllers;

import com.example.shop.controllers.request.ProductEvaluationRequest;
import com.example.shop.controllers.request.ProductInformationRequest;
import com.example.shop.controllers.response.ProductsListResponse;
import com.example.shop.controllers.response.ProductInformationResponse;
import org.springframework.http.ResponseEntity;

public interface ProductApi {
    ResponseEntity<ProductsListResponse> all();
    ResponseEntity<ProductInformationResponse> info(ProductInformationRequest request);
    ResponseEntity<Void> evaluation(ProductEvaluationRequest request);
}
