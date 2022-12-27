package com.example.shop.api.soap;

import com.example.shop.api.dto.request.CalculateFinalPriceRequest;
import com.example.shop.api.dto.request.GetStatisticsRequest;
import com.example.shop.api.dto.request.SaleRegistrationRequest;
import com.example.shop.api.dto.response.CalculateFinalPriceResponse;
import com.example.shop.api.dto.response.GetStatisticsResponse;
import com.example.shop.api.dto.response.SaleRegistrationResponse;
import com.example.shop.service.ProductService;
import com.example.shop.service.SaleFactService;
import com.example.shop.service.StatisticService;
import jakarta.jws.WebService;
import lombok.AllArgsConstructor;

@WebService(
        endpointInterface = "com.example.shop.api.soap.SaleSoapApi",
        serviceName = "sale"
)
@AllArgsConstructor
public class SaleSoapService implements SaleSoapApi {
    private final SaleFactService saleFactService;
    private final ProductService productService;
    private final StatisticService statisticService;

    @Override
    public CalculateFinalPriceResponse calculate(CalculateFinalPriceRequest request) {
        return productService.calculateFinalPrice(request.getClientId(), request.getProducts());
    }

    @Override
    public SaleRegistrationResponse registration(SaleRegistrationRequest request) {
        return saleFactService.saleRegistration(request.getProducts(), request.getFinalPriceKopecks());
    }

    @Override
    public GetStatisticsResponse statistic(GetStatisticsRequest request) {
        return statisticService.getStatistic(request);
    }
}
