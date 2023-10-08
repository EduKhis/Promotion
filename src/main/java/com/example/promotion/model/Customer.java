package com.example.promotion.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Customer {

    @Id
    private Long shipToCode;
    private String shipToName;
    private String chainName;

    public Customer() {
    }
    public Customer(Long shipToCode, String shipToName, String chainName) {
        this.shipToCode = shipToCode;
        this.shipToName = shipToName;
        this.chainName = chainName;
    }
}
