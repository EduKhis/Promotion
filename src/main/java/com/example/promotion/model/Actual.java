package com.example.promotion.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
public class Actual {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Customer customer;
    private int volume;
    private double actualSalesValue;
    @Enumerated(EnumType.STRING)
    private Marker marker;

    public Actual() {
    }

    public Actual(Date date, Product product, Customer customer, int volume, double actualSalesValue, Marker marker) {
        this.date = date;
        this.product = product;
        this.customer = customer;
        this.volume = volume;
        this.actualSalesValue = actualSalesValue;
        this.marker = marker;
    }

}
