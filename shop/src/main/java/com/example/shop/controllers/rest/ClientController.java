package com.example.shop.controllers.rest;

import com.example.shop.controllers.ClientApi;
import com.example.shop.controllers.request.ClientIndividualDiscountsRequest;
import com.example.shop.controllers.response.ClientListResponse;
import com.example.shop.service.ClientService;
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
@RequestMapping("/api/rest/client")
@RequiredArgsConstructor
public class ClientController implements ClientApi {
	private final ClientService clientService;

	@Override
	@Operation(summary = "Get all clients")
	@ApiResponse(
		responseCode = "200",
		content = {
			@Content(
				mediaType = "application/json",
				schema = @Schema(implementation = ClientListResponse.class)
			)
		}
	)
	@GetMapping
	public ResponseEntity<ClientListResponse> all() {
		return ResponseEntity.ok(new ClientListResponse(clientService.getAllClients()));
	}

	@Override
	@Operation(summary = "Change individual discounts for client")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Changes applied"),
		@ApiResponse(responseCode = "404", description = "The client with the specified ID was not found"),
	})
	@PutMapping("/discounts")
	public ResponseEntity<Void> discounts(@Valid @RequestBody ClientIndividualDiscountsRequest request) {
		clientService.updateClientDiscounts(request.getId(), request.getDiscountFirst(), request.getDiscountSecond());
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
