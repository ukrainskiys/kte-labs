package com.example.shop.service;

import com.example.shop.domain.ProductCountPair;
import com.example.shop.domain.dto.ProductDTO;
import com.example.shop.controllers.response.CalculateFinalPriceResponse;
import com.example.shop.controllers.response.ProductInformationResponse;

import java.util.List;

public interface ProductService {
	List<ProductDTO> getAllProducts();
	ProductInformationResponse getProductInfo(long clientId, long productId);
	CalculateFinalPriceResponse calculateFinalPrice(long clientId, List<ProductCountPair> products);
	void productEvaluation(long clientId, long productId, Integer rating);
}
