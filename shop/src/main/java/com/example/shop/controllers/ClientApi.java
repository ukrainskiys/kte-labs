package com.example.shop.controllers;

import com.example.shop.controllers.request.SetIndividualDiscountsRequest;
import com.example.shop.controllers.response.GetAllClientsResponse;

public interface ClientApi {
    GetAllClientsResponse all();
    void discounts(SetIndividualDiscountsRequest request);
}
