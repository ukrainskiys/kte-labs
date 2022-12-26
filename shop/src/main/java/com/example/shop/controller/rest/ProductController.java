package com.example.shop.controller.rest;

import com.example.shop.controller.dto.request.ProductEvaluationRequest;
import com.example.shop.controller.dto.request.ProductInformationRequest;
import com.example.shop.controller.dto.response.GetAllProductsResponse;
import com.example.shop.controller.dto.response.ProductInformationResponse;
import com.example.shop.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/product")
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;

	@GetMapping("/all")
	public GetAllProductsResponse getAll() {
		return new GetAllProductsResponse(productService.getAllProducts());
	}

	@PostMapping("/info")
	public ProductInformationResponse getProductInformation(@Valid @RequestBody ProductInformationRequest request) {
		return productService.getProductInfo(request.getClientId(), request.getProductId());
	}

	@PostMapping("/evaluation")
	public void evaluation(@Valid @RequestBody ProductEvaluationRequest request) {
		productService.productEvaluation(request.getClientId(), request.getProductId(), request.getRating());
	}
}
