package com.example.shop.api.soap;

import com.example.shop.api.SaleApi;
import com.example.shop.api.dto.request.CalculateFinalPriceRequest;
import com.example.shop.api.dto.request.GetStatisticsRequest;
import com.example.shop.api.dto.request.SaleRegistrationRequest;
import com.example.shop.api.dto.response.CalculateFinalPriceResponse;
import com.example.shop.api.dto.response.GetStatisticsResponse;
import com.example.shop.api.dto.response.SaleRegistrationResponse;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public interface SaleSoapApi extends SaleApi {
    @Override
    @WebMethod
    CalculateFinalPriceResponse calculate(CalculateFinalPriceRequest request);

    @Override
    @WebMethod
    SaleRegistrationResponse registration(SaleRegistrationRequest request);

    @Override
    @WebMethod
    GetStatisticsResponse statistic(GetStatisticsRequest request);
}
