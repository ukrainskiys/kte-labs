package com.example.shop.api.soap;

import com.example.shop.api.dto.request.SetIndividualDiscountsRequest;
import com.example.shop.api.dto.response.GetAllClientsResponse;
import com.example.shop.service.ClientService;
import jakarta.jws.WebService;
import lombok.RequiredArgsConstructor;

@WebService(
        endpointInterface = "com.example.shop.api.soap.ClientSoapApi",
        serviceName = "client"
)
@RequiredArgsConstructor
public class ClientSoapService implements ClientSoapApi {
    private final ClientService clientService;

    @Override
    public GetAllClientsResponse all() {
        return new GetAllClientsResponse(clientService.getAllClients());
    }

    @Override
    public void discounts(SetIndividualDiscountsRequest request) {
        clientService.updateClientDiscounts(request.getId(), request.getDiscountFirst(), request.getDiscountSecond());
    }
}
