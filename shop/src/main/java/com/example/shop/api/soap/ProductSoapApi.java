package com.example.shop.api.soap;

import com.example.shop.api.ProductApi;
import com.example.shop.api.dto.request.ProductEvaluationRequest;
import com.example.shop.api.dto.request.ProductInformationRequest;
import com.example.shop.api.dto.response.GetAllProductsResponse;
import com.example.shop.api.dto.response.ProductInformationResponse;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public interface ProductSoapApi extends ProductApi {
    @Override
    @WebMethod
    GetAllProductsResponse all();

    @Override
    @WebMethod
    ProductInformationResponse info(ProductInformationRequest request);

    @Override
    @WebMethod
    void evaluation(ProductEvaluationRequest request);
}
