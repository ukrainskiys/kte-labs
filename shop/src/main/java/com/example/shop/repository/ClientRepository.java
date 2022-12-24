package com.example.shop.repository;

import com.example.shop.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	@Query("SELECT c.id FROM Client c")
	List<Long> getAllIds();
}
