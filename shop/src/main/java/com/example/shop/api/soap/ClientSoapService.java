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
        validateSetIndividualDiscountsRequest(request);
        clientService.updateClientDiscounts(request.getId(), request.getDiscountFirst(), request.getDiscountSecond());
    }

    private void validateSetIndividualDiscountsRequest(SetIndividualDiscountsRequest request) {
        Integer first = request.getDiscountFirst();
        Integer second = request.getDiscountSecond();
        if ((first != null && (first < 0 || first > 100)) || (second != null && (second < 0 || second > 100))) {
            throw new RuntimeException("discount should be 0-100");
        }
    }
}
