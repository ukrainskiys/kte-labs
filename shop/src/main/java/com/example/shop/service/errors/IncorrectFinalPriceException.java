package com.example.shop.service.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class IncorrectFinalPriceException extends RuntimeException {
	public IncorrectFinalPriceException(long sum) {
		super(String.format("not found sale fact with sum=%d", sum));
	}
}
