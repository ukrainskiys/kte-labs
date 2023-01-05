package com.example.shop.repository;

import com.example.shop.domain.model.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistic, Long> {
	Optional<Statistic> findByClientId(long clientId);
	Optional<Statistic> findByProductId(long productId);
}
