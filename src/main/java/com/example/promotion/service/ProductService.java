package com.example.promotion.service;

import com.example.promotion.model.Products;
import com.example.promotion.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public Products findById (Long id) {
        return productRepository.findById(id).get();
    }
    public void initProducts () {
        try (BufferedReader br = new BufferedReader(new FileReader("products.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] array = line.split(";");
                Products products = new Products(Long.parseLong(array[0]), array[1], array[2], array[3]);
                productRepository.save(products);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
