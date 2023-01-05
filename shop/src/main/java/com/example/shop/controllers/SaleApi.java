package com.example.shop.controllers;

import com.example.shop.controllers.request.CalculateFinalPriceRequest;
import com.example.shop.controllers.request.GetStatisticsRequest;
import com.example.shop.controllers.request.SaleRegistrationRequest;
import com.example.shop.controllers.response.CalculateFinalPriceResponse;
import com.example.shop.controllers.response.GetStatisticsResponse;
import com.example.shop.controllers.response.SaleRegistrationResponse;

public interface SaleApi {
    CalculateFinalPriceResponse calculate(CalculateFinalPriceRequest request);
    SaleRegistrationResponse registration(SaleRegistrationRequest request);
    GetStatisticsResponse statistic(GetStatisticsRequest request);
}
