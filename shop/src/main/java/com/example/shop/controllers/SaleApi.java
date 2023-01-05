package com.example.shop.controllers;

import com.example.shop.controllers.request.SaleCalculateRequest;
import com.example.shop.controllers.request.SaleStatisticsRequest;
import com.example.shop.controllers.request.SaleRegistrationRequest;
import com.example.shop.controllers.response.SaleCalculateResponse;
import com.example.shop.controllers.response.SaleStatisticsResponse;
import com.example.shop.controllers.response.SaleRegistrationResponse;
import org.springframework.http.ResponseEntity;

public interface SaleApi {
    ResponseEntity<SaleCalculateResponse> calculate(SaleCalculateRequest request);
    ResponseEntity<SaleRegistrationResponse> registration(SaleRegistrationRequest request);
    ResponseEntity<SaleStatisticsResponse> statistic(SaleStatisticsRequest request);
}
