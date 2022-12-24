package com.example.shop.service.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IncorrectGetStatisticResponseException extends RuntimeException {
    public IncorrectGetStatisticResponseException() {
        super("invalid request: enter product_id or client_id");
    }
}
