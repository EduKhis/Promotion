package com.example.promotion.repository;

import com.example.promotion.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long> {
    boolean existsByChainNameAndProductsMaterialNo(String shipName, Long id);

    Price findByChainNameAndProductsMaterialNo(String shipName, Long id);
}
