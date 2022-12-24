package com.example.shop.service;

import com.example.shop.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {
	private final RatingRepository ratingRepository;

	@Override
	public Integer getRatingByClientIdAndProductId(long clientId, long productId) {
		return ratingRepository.findRatingByClientIdAndProductId(clientId, productId).orElse(null);
	}

	@Override
	public void productEvaluation(long clientId, long productId, Integer rating) {
		if (rating == null) {
			ratingRepository.deleteByClientIdAndProductId(clientId, productId);
		} else {
			ratingRepository.updateRating(clientId, productId, rating);
		}
	}
}
