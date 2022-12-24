package com.example.shop.repository;

import com.example.shop.model.entity.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistic, Long> {
	Optional<Statistic> findByClientId(long clientId);
	Optional<Statistic> findByProductId(long productId);
	@Query("SELECT s.dateUpdate FROM Statistic s WHERE s.clientId = ?1")
	Optional<LocalDateTime> findDateUpdateByClientId(long clientId);
}
