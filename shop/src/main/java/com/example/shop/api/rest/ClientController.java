package com.example.shop.api.rest;

import com.example.shop.api.ClientApi;
import com.example.shop.api.dto.request.SetIndividualDiscountsRequest;
import com.example.shop.api.dto.response.GetAllClientsResponse;
import com.example.shop.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rest/client")
@RequiredArgsConstructor
public class ClientController implements ClientApi {
	private final ClientService clientService;

	@Override
	@GetMapping
	public GetAllClientsResponse all() {
		return new GetAllClientsResponse(clientService.getAllClients());
	}

	@Override
	@PutMapping("/discounts")
	public void discounts(@Valid @RequestBody SetIndividualDiscountsRequest request) {
		clientService.updateClientDiscounts(request.getId(), request.getDiscountFirst(), request.getDiscountSecond());
	}
}
