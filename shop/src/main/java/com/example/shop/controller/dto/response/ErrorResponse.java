package com.example.shop.controller.dto.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ErrorResponse extends AbstractResponse {
	private String message;
}
