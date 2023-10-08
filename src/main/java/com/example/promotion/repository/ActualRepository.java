package com.example.promotion.repository;

import com.example.promotion.dto.DtoActualByDay;
import com.example.promotion.dto.DtoActualByPromo;
import com.example.promotion.model.Actual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActualRepository extends JpaRepository<Actual, Long> {
    @Query(value = "SELECT ac.date AS date, ac.product_material_no AS productId, c.chain_name AS chainName," +
            "ac.volume AS volume,ac.actual_sales_value AS actualSalesValue FROM Actual AS ac " +
            "INNER JOIN Customer AS c ON ac.customer_ship_to_code = c.ship_to_code " +
            "WHERE ac.product_material_no=?1 AND  c.chain_name = ?2 ", nativeQuery = true)
    List<DtoActualByDay> uploadActualSalesByChainNameAndProduct(Long id, String chainName);

    @Query(value = "SELECT c.chain_name AS chainName, p.category_code AS categoryCode, MONTH(ac.date) AS month, " +
            "SUM(CASE WHEN marker='REGULAR' THEN ac.volume ELSE 0 END) AS volumeRegular, " +
            "SUM(CASE WHEN marker='PROMO' THEN ac.volume ELSE 0 END) AS volumePromo, " +
            "ROUND(SUM(CASE WHEN marker='PROMO' THEN ac.volume ELSE 0 END)/SUM(volume)*100) AS percent " +
            "FROM promotion_db.actual AS ac " +
            "INNER JOIN customer AS c ON ac.customer_ship_to_code = c.ship_to_code " +
            "INNER JOIN product AS p ON ac.product_material_no = p.material_no " +
            "GROUP BY MONTH(date), chain_name, category_code " +
            "ORDER BY chain_name, category_code , MONTH(date)" , nativeQuery = true)
    List<DtoActualByPromo> uploadActualSalesByPromo();
}
