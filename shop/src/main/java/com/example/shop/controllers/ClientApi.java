package com.example.shop.controllers;

import com.example.shop.controllers.request.ClientIndividualDiscountsRequest;
import com.example.shop.controllers.response.ClientListResponse;
import org.springframework.http.ResponseEntity;

public interface ClientApi {
    ResponseEntity<ClientListResponse> all();
    ResponseEntity<Void> discounts(ClientIndividualDiscountsRequest request);
}
