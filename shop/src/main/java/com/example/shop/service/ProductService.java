package com.example.shop.service;

import com.example.shop.domain.ProductCountPair;
import com.example.shop.domain.dto.ProductDTO;
import com.example.shop.controllers.response.SaleCalculateResponse;
import com.example.shop.controllers.response.ProductInformationResponse;

import java.util.List;

public interface ProductService {
	List<ProductDTO> getAllProducts();
	ProductInformationResponse getProductInfo(long clientId, long productId);
	SaleCalculateResponse calculateFinalPrice(long clientId, List<ProductCountPair> products);
	void productEvaluation(long clientId, long productId, Integer rating);
}
