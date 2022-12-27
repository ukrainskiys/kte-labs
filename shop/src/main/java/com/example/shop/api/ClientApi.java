package com.example.shop.api;

import com.example.shop.api.dto.request.SetIndividualDiscountsRequest;
import com.example.shop.api.dto.response.GetAllClientsResponse;

public interface ClientApi {
    GetAllClientsResponse all();
    void discounts(SetIndividualDiscountsRequest request);
}
