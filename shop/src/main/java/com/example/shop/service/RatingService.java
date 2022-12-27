package com.example.shop.service;

import com.example.shop.model.entity.Rating;

import java.util.List;

public interface RatingService {
	Integer getRatingByClientIdAndProductId(long clientId, long productId);
	List<Rating> getAllByProductId(long productId);
	void productEvaluation(long clientId, long productId, Integer rating);
}
