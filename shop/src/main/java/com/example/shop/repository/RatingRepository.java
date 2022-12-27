package com.example.shop.repository;

import com.example.shop.model.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
	List<Rating> findAllByProductId(long productId);
	Optional<Rating> findByClientIdAndProductId(long clientId, long productId);
	void deleteByClientIdAndProductId(long clientId, long productId);
}
