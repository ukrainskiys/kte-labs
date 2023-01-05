package com.example.shop.repository;

import com.example.shop.domain.model.SaleFactPosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleFactPositionRepository extends JpaRepository<SaleFactPosition, Long> {
}
