package com.example.shop.api.rest;

import com.example.shop.api.SaleApi;
import com.example.shop.api.dto.request.CalculateFinalPriceRequest;
import com.example.shop.api.dto.request.GetStatisticsRequest;
import com.example.shop.api.dto.request.SaleRegistrationRequest;
import com.example.shop.api.dto.response.CalculateFinalPriceResponse;
import com.example.shop.api.dto.response.GetStatisticsResponse;
import com.example.shop.api.dto.response.SaleRegistrationResponse;
import com.example.shop.service.ProductService;
import com.example.shop.service.SaleFactService;
import com.example.shop.service.StatisticService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rest/sale")
@RequiredArgsConstructor
public class SaleController implements SaleApi {
    private final SaleFactService saleFactService;
    private final ProductService productService;
    private final StatisticService statisticService;

    @Override
    @PostMapping("/calculate")
    public CalculateFinalPriceResponse calculate(@Valid @RequestBody CalculateFinalPriceRequest request) {
        return productService.calculateFinalPrice(request.getClientId(), request.getProducts());
    }

    @Override
    @PostMapping("/registration")
    public SaleRegistrationResponse registration(@Valid @RequestBody SaleRegistrationRequest request) {
        return saleFactService.saleRegistration(request.getProducts(), request.getFinalPriceKopecks());
    }

    @Override
    @PostMapping("/statistic")
    public GetStatisticsResponse statistic(@Valid @RequestBody GetStatisticsRequest request) {
        return statisticService.getStatistic(request);
    }
}
