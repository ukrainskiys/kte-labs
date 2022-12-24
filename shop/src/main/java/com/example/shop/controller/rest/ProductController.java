package com.example.shop.controller.rest;

import com.example.shop.controller.dto.response.ProductInformationResponse;
import com.example.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/product")
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;

	@GetMapping("/info")
	public ProductInformationResponse getProductInformation(
		@RequestParam(value = "product_id") Long productId,
		@RequestParam(value = "client_id") Long clientId
	) {
		return productService.getProductInfo(clientId, productId);
	}
}
