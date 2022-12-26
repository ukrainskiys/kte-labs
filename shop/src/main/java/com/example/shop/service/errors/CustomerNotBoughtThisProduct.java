package com.example.shop.service.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomerNotBoughtThisProduct extends RuntimeException {
    public CustomerNotBoughtThisProduct(long clientId, long productId) {
        super(String.format("customer[%d] don't bought this product[%d]", clientId, productId));
    }
}
