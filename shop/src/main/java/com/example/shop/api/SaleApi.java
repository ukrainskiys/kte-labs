package com.example.shop.api;

import com.example.shop.api.dto.request.CalculateFinalPriceRequest;
import com.example.shop.api.dto.request.GetStatisticsRequest;
import com.example.shop.api.dto.request.SaleRegistrationRequest;
import com.example.shop.api.dto.response.CalculateFinalPriceResponse;
import com.example.shop.api.dto.response.GetStatisticsResponse;
import com.example.shop.api.dto.response.SaleRegistrationResponse;

public interface SaleApi {
    CalculateFinalPriceResponse calculate(CalculateFinalPriceRequest request);
    SaleRegistrationResponse registration(SaleRegistrationRequest request);
    GetStatisticsResponse statistic(GetStatisticsRequest request);
}
