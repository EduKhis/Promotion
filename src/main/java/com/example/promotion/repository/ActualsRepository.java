package com.example.promotion.repository;

import com.example.promotion.dto.DtoActualsPromo;
import com.example.promotion.dto.DtoActualsDayTwo;
import com.example.promotion.model.Actuals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActualsRepository extends JpaRepository<Actuals, Long> {
    @Query(value = "SELECT ac.date AS date, ac.products_material_no AS productId, c.chain_name AS chainName," +
            "ac.value AS value,ac.sales_value AS salesValue FROM Actuals AS ac " +
            "INNER JOIN Customers AS c ON ac.customers_ship_to_code = c.ship_to_code " +
            "WHERE ac.products_material_no=?1 AND  c.chain_name = ?2 ", nativeQuery = true)
    List<DtoActualsDayTwo> uploadFactByProductAndChainName(Long id, String chainName);

    @Query(value = "SELECT c.chain_name AS chainName, p.category_code AS categoryCode, MONTH(ac.date) AS month, SUM(ac.value) AS value, SUM(CASE WHEN marker=0 THEN ac.value ELSE 0 END) AS valuePromo, " +
            "ROUND(SUM(CASE WHEN marker=0 THEN ac.value ELSE 0 END)/SUM(value)*100) AS percent " +
            "FROM promotion_db.actuals AS ac " +
            "INNER JOIN customers AS c ON ac.customers_ship_to_code = c.ship_to_code " +
            "INNER JOIN products AS p ON ac.products_material_no = p.material_no " +
            "GROUP BY MONTH(date), chain_name, category_code " +
            "ORDER BY chain_name, category_code , MONTH(date)" , nativeQuery = true)
    List<DtoActualsPromo> uploadFact();
}
