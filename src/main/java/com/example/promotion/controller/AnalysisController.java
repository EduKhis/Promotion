package com.example.promotion.controller;

import com.example.promotion.dto.DtoActualByPromo;
import com.example.promotion.dto.DtoActualByDay;
import com.example.promotion.service.ActualService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class AnalysisController {
    ActualService actualService;

    @GetMapping("/uploadByPromo")
    public ResponseEntity<?> uploadByPromo() {
        List<DtoActualByPromo> list = actualService.uploadActualSalesByPromo();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @PostMapping("/uploadByDay")
    public ResponseEntity<?> uploadByDay(@RequestParam(name = "idProduct") Long idProduct, @RequestParam(name = "chainName") String chainName) {
        List<DtoActualByDay> list = actualService.uploadActualSalesByChainNameAndProduct(idProduct, chainName);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
}
