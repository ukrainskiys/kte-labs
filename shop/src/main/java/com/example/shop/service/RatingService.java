package com.example.shop.service;

public interface RatingService {
	Integer getRatingByClientIdAndProductId(long clientId, long productId);
	void productEvaluation(long clientId, long productId, Integer rating);
}
