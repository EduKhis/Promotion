package com.example.promotion.service;

import com.example.promotion.model.Customers;
import com.example.promotion.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public void initCustomers () {
        try (BufferedReader br = new BufferedReader(new FileReader("customers.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] array = line.split(";");
                Customers customers = new Customers(Long.parseLong(array[0]), array[1],array[2]);
                customerRepository.save(customers);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
