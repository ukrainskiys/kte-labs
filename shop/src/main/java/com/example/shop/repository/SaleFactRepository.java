package com.example.shop.repository;

import com.example.shop.domain.model.SaleFact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface SaleFactRepository extends JpaRepository<SaleFact, Long> {
	@Query(
		nativeQuery = true,
		value = "SELECT check_number FROM sale_facts WHERE sale_date > ? ORDER BY sale_date DESC LIMIT 1"
	)
	Optional<String> findLastCheckToday(LocalDateTime saleDate);
}
