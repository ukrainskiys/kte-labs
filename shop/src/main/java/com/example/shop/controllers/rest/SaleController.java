package com.example.shop.controllers.rest;

import com.example.shop.controllers.SaleApi;
import com.example.shop.controllers.request.SaleCalculateRequest;
import com.example.shop.controllers.request.SaleStatisticsRequest;
import com.example.shop.controllers.request.SaleRegistrationRequest;
import com.example.shop.controllers.response.SaleCalculateResponse;
import com.example.shop.controllers.response.SaleStatisticsResponse;
import com.example.shop.controllers.response.SaleRegistrationResponse;
import com.example.shop.service.ProductService;
import com.example.shop.service.SaleFactService;
import com.example.shop.service.StatisticService;

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
@RequestMapping("/api/rest/sale")
@RequiredArgsConstructor
public class SaleController implements SaleApi {
	private final SaleFactService saleFactService;
	private final ProductService productService;
	private final StatisticService statisticService;

	@Override
	@Operation(summary = "Request for a total cost calculation")
	@ApiResponses({
		@ApiResponse(
			responseCode = "201",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = SaleCalculateResponse.class)
			)
		),
		@ApiResponse(
			responseCode = "404",
			description = "Client or Product with the specified ID was not found",
			content = @Content
		),
	})
	@PostMapping("/calculate")
	public ResponseEntity<SaleCalculateResponse> calculate(@Valid @RequestBody SaleCalculateRequest request) {
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(productService.calculateFinalPrice(request.getClientId(), request.getProducts()));
	}

	@Override
	@Operation(summary = "Request to register a sale")
	@ApiResponses({
		@ApiResponse(
			responseCode = "201",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = SaleRegistrationResponse.class)
			)
		),
		@ApiResponse(
			responseCode = "400",
			description = "Specified value does not exist",
			content = @Content
		),
		@ApiResponse(
			responseCode = "404",
			description = "Client or Product with the specified ID was not found",
			content = @Content
		),
	})
	@PostMapping("/registration")
	public ResponseEntity<SaleRegistrationResponse> registration(@Valid @RequestBody SaleRegistrationRequest request) {
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(saleFactService.saleRegistration(request.getProducts(), request.getFinalPriceKopecks()));
	}

	@Override
	@Operation(summary = "Obtaining statistics of client/product purchases")
	@ApiResponses({
		@ApiResponse(
			responseCode = "201",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = SaleStatisticsResponse.class)
			)
		),
		@ApiResponse(
			responseCode = "400",
			description = "Invalid input data",
			content = @Content
		),
		@ApiResponse(
			responseCode = "404",
			description = "Client or Product with the specified ID was not found",
			content = @Content
		),
	})
	@PostMapping("/statistic")
	public ResponseEntity<SaleStatisticsResponse> statistic(@Valid @RequestBody SaleStatisticsRequest request) {
		return ResponseEntity.ok(statisticService.getStatistic(request));
	}
}
