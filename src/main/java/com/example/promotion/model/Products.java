package com.example.promotion.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Products {
    @Id
    private Long materialNo;
    private String materialDesc;
    private String categoryCode;
    private String categoryName;

    public Products(Long materialNo, String materialDesc, String categoryCode, String categoryName) {
        this.materialNo = materialNo;
        this.materialDesc = materialDesc;
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
    }

    public Products() {

    }
}
