package com.example.promotion.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
public class Actuals {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;
    @ManyToOne
    private Products products;
    @ManyToOne
    private Customers customers;
    private int value;
    private double salesValue;
    @Enumerated(EnumType.STRING)
    private Marker marker;

    public Actuals() {
    }

    public Actuals(Date date, Products products, Customers customers, int value, double salesValue, Marker marker) {
        this.date = date;
        this.products = products;
        this.customers = customers;
        this.value = value;
        this.salesValue = salesValue;
        this.marker = marker;
    }

}
