package com.example.promotion.service;

import com.example.promotion.dto.DtoActualsPromo;
import com.example.promotion.dto.DtoActualsDay;
import com.example.promotion.model.*;
import com.example.promotion.repository.ActualsRepository;
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
public class ActualsService {
    @Autowired
    ActualsRepository actualsRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    PriceRepository priceRepository;


    public List<DtoActualsDay> uploadFactByProductAndChainName(Long id, String chainName){
        return actualsRepository.uploadFactByProductAndChainName(id, chainName);
    }
    public List<DtoActualsPromo> uploadFact(){
        return actualsRepository.uploadFact();
    }

    public void initActuals() {
        try (BufferedReader br = new BufferedReader(new FileReader("actuals.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] array = line.split(";");
                if (productRepository.existsById(Long.parseLong(array[1])) && customerRepository.existsById(Long.parseLong(array[2]))) {
                    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = ft.parse(array[0]);
                    Products products = productRepository.findById(Long.parseLong(array[1])).get();
                    Customers customers = customerRepository.findById(Long.parseLong(array[2])).get();
                    int volume = Integer.parseInt(array[4]);
                    double actualPrice =Double.parseDouble(array[5]);
                    Marker marker = createMarker(volume,actualPrice, customers.getChainName(), products.getMaterialNo());
                    Actuals actuals = new Actuals(date, products, customers, volume, actualPrice,marker);
                    actualsRepository.save(actuals);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public Marker createMarker (int volume, double actualPrice, String chainName, Long idProduct) {
        double regularPrice = priceRepository.findByChainNameAndProductsMaterialNo(chainName,idProduct).getRegularPrice();
        if (actualPrice/volume<regularPrice) {
            return Marker.PROMO;
        }
        return Marker.REGULAR;
    }

}
