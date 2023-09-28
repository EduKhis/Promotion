package com.example.promotion.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Customers {

    @Id
    private Long shipToCode;
    private String stringShipToName;
    private String chainName;

    public Customers() {
    }

    public Customers(Long shipToCode, String stringShipToName, String chainName) {
        this.shipToCode = shipToCode;
        this.stringShipToName = stringShipToName;
        this.chainName = chainName;
    }
}
