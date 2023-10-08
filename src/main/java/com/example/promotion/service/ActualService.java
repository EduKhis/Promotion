package com.example.promotion.service;

import com.example.promotion.dto.DtoActualByDay;
import com.example.promotion.dto.DtoActualByPromo;
import com.example.promotion.model.*;
import com.example.promotion.repository.ActualRepository;
import com.example.promotion.repository.CustomerRepository;
import com.example.promotion.repository.PriceRepository;
import com.example.promotion.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ActualService {
    @Autowired
    ActualRepository actualRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    PriceRepository priceRepository;


    public List<DtoActualByDay> uploadActualSalesByChainNameAndProduct(Long id, String chainName){
        return actualRepository.uploadActualSalesByChainNameAndProduct(id, chainName);
    }
    public List<DtoActualByPromo> uploadActualSalesByPromo(){
        return actualRepository.uploadActualSalesByPromo();
    }
    public void initActuals() {
        try (BufferedReader br = new BufferedReader(new FileReader("actuals.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] array = line.split(";");
                if (productRepository.existsById(Long.parseLong(array[1])) && customerRepository.existsById(Long.parseLong(array[2]))) {
                    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = ft.parse(array[0]);
                    Product product = productRepository.findById(Long.parseLong(array[1])).get();
                    Customer customer = customerRepository.findById(Long.parseLong(array[2])).get();
                    int volume = Integer.parseInt(array[4]);
                    double actualSalesValue =Double.parseDouble(array[5]);
                    Marker marker = createMarker(volume,actualSalesValue, customer.getChainName(), product.getMaterialNo());
                    Actual actual = new Actual(date, product, customer, volume, actualSalesValue,marker);
                    actualRepository.save(actual);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public Marker createMarker(int volume, double actualSalesValue, String chainName, Long idProduct) {
        double regularPrice = priceRepository.findByChainNameAndProductMaterialNo(chainName,idProduct).getRegularPrice();
        if (actualSalesValue/volume<regularPrice) {
            return Marker.PROMO;
        }
        return Marker.REGULAR;
    }
}
