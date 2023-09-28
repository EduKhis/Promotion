package com.example.promotion.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String chainName;
    @ManyToOne
    private Products products;
    private double regularPrice;

    public Price(String chainName, Products products, double regularPrice) {
        this.chainName = chainName;
        this.products=products;
        this.regularPrice = regularPrice;
    }

    public Price() {

    }
}
