package com.example.shop.api.soap;

import com.example.shop.api.ClientApi;
import com.example.shop.api.dto.request.SetIndividualDiscountsRequest;
import com.example.shop.api.dto.response.GetAllClientsResponse;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public interface ClientSoapApi extends ClientApi {
    @Override
    @WebMethod
    GetAllClientsResponse all();

    @Override
    @WebMethod
    void discounts(SetIndividualDiscountsRequest request);
}
