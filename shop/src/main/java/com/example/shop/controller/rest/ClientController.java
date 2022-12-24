package com.example.shop.controller.rest;

import com.example.shop.controller.dto.request.SetIndividualDiscountsRequest;
import com.example.shop.controller.dto.response.GetAllClientsResponse;
import com.example.shop.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/client")
@RequiredArgsConstructor
public class ClientController {
	private final ClientService clientService;

	@GetMapping("/all")
	public GetAllClientsResponse getAll() {
		return GetAllClientsResponse.builder()
			.clients(clientService.getAllClients())
			.build();
	}

	@PutMapping("/discounts")
	public void setIndividualDiscounts(SetIndividualDiscountsRequest request) {
		clientService.updateClientDiscounts(request.getId(), request.getDiscountFirst(), request.getDiscountSecond());
	}
}
