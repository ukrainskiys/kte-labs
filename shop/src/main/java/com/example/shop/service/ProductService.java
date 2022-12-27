package com.example.shop.service;

import com.example.shop.model.ProductDTO;
import com.example.shop.model.SalePair;
import com.example.shop.api.dto.response.CalculateFinalPriceResponse;
import com.example.shop.api.dto.response.ProductInformationResponse;

import java.util.List;

public interface ProductService {
	List<ProductDTO> getAllProducts();
	ProductInformationResponse getProductInfo(long clientId, long productId);
	CalculateFinalPriceResponse calculateFinalPrice(long clientId, List<SalePair> products);
	void productEvaluation(long clientId, long productId, Integer rating);
}
