package com.example.shop.repository;

import com.example.shop.model.entity.SaleFact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SaleFactRepository extends JpaRepository<SaleFact, Long> {
	List<SaleFact> getAllByClientId(long clientId);

	List<SaleFact> getAllByClientIdAndCheckNumberNotNullAndSaleDateAfter(long clientId, LocalDateTime dateTime);

	@Query(
		nativeQuery = true,
		value = """
			SELECT * FROM sale_facts WHERE
			check_number IS NOT NULL
			AND sale_date > ?2
			AND ?1 IN (SELECT * FROM json_to_recordset(positions) AS x(product_id bigint))"""
	)
	List<SaleFact> getAllProductsWithoutStatistics(long productId, LocalDateTime dateTime);

	@Query(
		nativeQuery = true,
		value = "SELECT check_number FROM sale_facts WHERE sale_date > ? ORDER BY sale_date DESC LIMIT 1"
	)
	Optional<String> findLastCheckToday(LocalDateTime saleDate);

	@Query(
		nativeQuery = true,
		value = "SELECT * FROM sale_facts WHERE check_number IS NULL AND jsonb_array_length(positions) = ?"
	)
	List<SaleFact> findAllNotProcessedByCountPositions(int count);
}
