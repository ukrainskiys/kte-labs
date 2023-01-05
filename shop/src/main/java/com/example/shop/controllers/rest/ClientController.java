package com.example.shop.controllers.rest;

import com.example.shop.controllers.ClientApi;
import com.example.shop.controllers.request.SetIndividualDiscountsRequest;
import com.example.shop.controllers.response.GetAllClientsResponse;
import com.example.shop.service.ClientService;
import javax.validation.Valid;
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
