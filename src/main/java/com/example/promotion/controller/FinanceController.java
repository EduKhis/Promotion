package com.example.promotion.controller;

import com.example.promotion.model.*;
import com.example.promotion.service.ActualsService;
import com.example.promotion.service.CustomerService;
import com.example.promotion.service.PriceService;
import com.example.promotion.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class FinanceController {
    PriceService priceService;
    ProductService productService;
    CustomerService customerService;
    ActualsService actualsService;


    @GetMapping("/prices")
    public ResponseEntity<?> getAllPrice() {
        List<Price> list = priceService.findAll();
        return new ResponseEntity<>(list, HttpStatus.CREATED);
    }

    @DeleteMapping("/prices")
    public ResponseEntity<?> deletePrice(@RequestParam(name = "id") Long idProduct, @RequestParam(name = "chainName") String chainName) {
        try {
            if (!priceService.existsByChainNameAndProductsMaterialNo(chainName,idProduct)){
                return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
            }
            Price price =priceService.findByChainNameAndProductsMaterialNo(chainName,idProduct);
            priceService.delete(price);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/prices")
    public ResponseEntity<?> addPrice(@RequestParam(name = "id") Long idProduct, @RequestParam(name = "chainName") String chainName
                                      ,@RequestParam(name = "regularPrice") double regularPrice) {
        try {
            if (priceService.existsByChainNameAndProductsMaterialNo(chainName,idProduct)){
                return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
            }
            Products products = productService.findById(idProduct);
            Price price = new Price(chainName, products, regularPrice);
            priceService.save(price);
            return new ResponseEntity<>(price, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping ("/addProducts")
    public ResponseEntity<?> addProducts () {
        productService.initProducts();
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping ("/addCustomers")
    public ResponseEntity<?> addCustomers () {
        customerService.initCustomers();
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping ("/addPrices")
    public ResponseEntity<?> addPrices () {
        priceService.initPrices();
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping ("/addActuals")
    public ResponseEntity<?> addActuals () {
        actualsService.initActuals();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
