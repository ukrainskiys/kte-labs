package com.example.shop.controller.rest;

import com.example.shop.controller.dto.request.CalculateFinalPriceRequest;
import com.example.shop.controller.dto.request.GetStatisticsRequest;
import com.example.shop.controller.dto.request.SaleRegistrationRequest;
import com.example.shop.controller.dto.response.CalculateFinalPriceResponse;
import com.example.shop.controller.dto.response.GetStatisticsResponse;
import com.example.shop.controller.dto.response.SaleRegistrationResponse;
import com.example.shop.service.ProductService;
import com.example.shop.service.SaleFactService;
import com.example.shop.service.StatisticService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/sale")
@RequiredArgsConstructor
public class SaleController {
    private final SaleFactService saleFactService;
    private final ProductService productService;
    private final StatisticService statisticService;

    @PostMapping("/calculate")
    public CalculateFinalPriceResponse calculate(@Valid @RequestBody CalculateFinalPriceRequest request) {
        return productService.calculateFinalPrice(request.getClientId(), request.getProducts());
    }

    @PostMapping("/registration")
    public SaleRegistrationResponse registration(@Valid @RequestBody SaleRegistrationRequest request) {
        return saleFactService.saleRegistration(request.getProducts(), request.getFinalPriceKopecks());
    }

    @PostMapping("/statistic")
    public GetStatisticsResponse statistic(@Valid @RequestBody GetStatisticsRequest request) {
        return statisticService.getStatistic(request);
    }
}
