package com.example.shop.repository;

import com.example.shop.model.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
	Optional<Integer> findRatingByClientIdAndProductId(long clientId, long productId);
	void deleteByClientIdAndProductId(long clientId, long productId);
	@Modifying
	@Query("UPDATE Rating r SET r.rating = ?3 WHERE r.client.id = ?1 AND r.product.id = ?2")
	void updateRating(long clientId, long productId, Integer rating);
}
