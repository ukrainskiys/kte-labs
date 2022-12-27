package com.example.shop.api.soap;

import com.example.shop.api.dto.request.ProductEvaluationRequest;
import com.example.shop.api.dto.request.ProductInformationRequest;
import com.example.shop.api.dto.response.GetAllProductsResponse;
import com.example.shop.api.dto.response.ProductInformationResponse;
import com.example.shop.service.ProductService;
import jakarta.jws.WebService;
import lombok.AllArgsConstructor;

@WebService(
        endpointInterface = "com.example.shop.api.soap.ProductSoapApi",
        serviceName = "product"
)
@AllArgsConstructor
public class ProductSoapService implements ProductSoapApi {
    private final ProductService productService;

    @Override
    public GetAllProductsResponse all() {
        return new GetAllProductsResponse(productService.getAllProducts());
    }

    @Override
    public ProductInformationResponse info(ProductInformationRequest request) {
        return productService.getProductInfo(request.getClientId(), request.getProductId());
    }

    @Override
    public void evaluation(ProductEvaluationRequest request) {
        validateProductEvaluationRequest(request);
        productService.productEvaluation(request.getClientId(), request.getProductId(), request.getRating());
    }

    private void validateProductEvaluationRequest(ProductEvaluationRequest request) {
        Integer rating = request.getRating();
        if (rating != null && (rating < 1 || rating > 5)) {
            throw new RuntimeException("rating should be 1-5 or null");
        }
    }
}
