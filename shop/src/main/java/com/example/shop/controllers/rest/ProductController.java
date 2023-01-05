package com.example.shop.controllers.rest;

import com.example.shop.controllers.ProductApi;
import com.example.shop.controllers.request.ProductEvaluationRequest;
import com.example.shop.controllers.request.ProductInformationRequest;
import com.example.shop.controllers.response.ProductsListResponse;
import com.example.shop.controllers.response.ProductInformationResponse;
import com.example.shop.service.ProductService;

import javax.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ControllerAdvice
@RequestMapping("/api/rest/products")
@RequiredArgsConstructor
public class ProductController implements ProductApi {
	private final ProductService productService;

	@Override
	@Operation(summary = "Get all products")
	@ApiResponse(
		responseCode = "200",
		content = @Content(
			mediaType = "application/json",
			schema = @Schema(implementation = ProductsListResponse.class)
		)
	)
	@GetMapping
	public ResponseEntity<ProductsListResponse> all() {
		return ResponseEntity.ok(new ProductsListResponse(productService.getAllProducts()));
	}

	@Override
	@Operation(summary = "Get full information for product")
	@ApiResponses({
		@ApiResponse(
			responseCode = "200",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = ProductInformationResponse.class)
			)
		),
		@ApiResponse(
			responseCode = "404",
			description = "Client or Product with the specified ID was not found",
			content = @Content
		),
	})
	@PostMapping("/info")
	public ResponseEntity<ProductInformationResponse> info(@Valid @RequestBody ProductInformationRequest request) {
		return ResponseEntity.ok(productService.getProductInfo(request.getClientId(), request.getProductId()));
	}

	@Override
	@Operation(summary = "Create a product valuation")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Success"),
		@ApiResponse(responseCode = "400", description = "Customer don't bought this product"),
		@ApiResponse(responseCode = "404", description = "Client or Product with the specified ID was not found"),
	})
	@PostMapping("/evaluation")
	public ResponseEntity<Void> evaluation(@Valid @RequestBody ProductEvaluationRequest request) {
		productService.productEvaluation(request.getClientId(), request.getProductId(), request.getRating());
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
