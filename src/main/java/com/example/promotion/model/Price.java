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
    private Product product;
    private double regularPrice;

    public Price(String chainName, Product product, double regularPrice) {
        this.chainName = chainName;
        this.product = product;
        this.regularPrice = regularPrice;
    }
    public Price() {

    }
}
