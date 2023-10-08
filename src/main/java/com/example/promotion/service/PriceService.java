package com.example.promotion.service;

import com.example.promotion.model.Price;
import com.example.promotion.model.Product;
import com.example.promotion.repository.PriceRepository;
import com.example.promotion.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service
public class PriceService {
    @Autowired
    PriceRepository priceRepository;
    @Autowired
    ProductRepository productRepository;

    public List<Price> findAll() {
        return priceRepository.findAll();
    }

    public void save(Price price) {
        priceRepository.save(price);
    }
    public boolean existsById(Long id){
        return priceRepository.existsById(id);
    }
    public Price findById (Long id) {
        return priceRepository.findById(id).get();
    }
    public boolean isValidPriceByChainNameAndProductMaterialNo(String chainName, Long id) {
        return priceRepository.existsByChainNameAndProductMaterialNo(chainName, id);
    }
    public void delete(Price price) {
        priceRepository.delete(price);
    }
    public void initPrices() {
        try (BufferedReader br = new BufferedReader(new FileReader("price.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] array = line.split(";");
                if (productRepository.existsById(Long.parseLong(array[1]))) {
                    Product product = productRepository.findById(Long.parseLong(array[1])).get();
                    Price price = new Price(array[0], product, Double.parseDouble(array[2]));
                    priceRepository.save(price);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
