package com.example.shop.controller.dto.request;

import com.example.shop.model.SalePair;

import java.util.List;

public class CalculateFinalPriceRequest {
	private long clientId;
	private List<SalePair> products;
}
