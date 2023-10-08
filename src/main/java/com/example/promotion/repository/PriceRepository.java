package com.example.promotion.repository;

import com.example.promotion.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long> {
    boolean existsByChainNameAndProductMaterialNo(String chainName, Long id);

    Price findByChainNameAndProductMaterialNo(String chainName, Long id);
}
