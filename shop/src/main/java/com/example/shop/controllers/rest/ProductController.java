package com.example.shop.controllers.rest;

import com.example.shop.controllers.ProductApi;
import com.example.shop.controllers.request.ProductEvaluationRequest;
import com.example.shop.controllers.request.ProductInformationRequest;
import com.example.shop.controllers.response.GetAllProductsResponse;
import com.example.shop.controllers.response.ProductInformationResponse;
import com.example.shop.service.ProductService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rest/products")
@RequiredArgsConstructor
public class ProductController implements ProductApi {
	private final ProductService productService;

	@Override
	@GetMapping
	public GetAllProductsResponse all() {
		return new GetAllProductsResponse(productService.getAllProducts());
	}

	@Override
	@PostMapping("/info")
	public ProductInformationResponse info(@Valid @RequestBody ProductInformationRequest request) {
		return productService.getProductInfo(request.getClientId(), request.getProductId());
	}

	@Override
	@PostMapping("/evaluation")
	public void evaluation(@Valid @RequestBody ProductEvaluationRequest request) {
		productService.productEvaluation(request.getClientId(), request.getProductId(), request.getRating());
	}
}
