package com.example.promotion.controller;

import com.example.promotion.dto.DtoActualsPromo;
import com.example.promotion.dto.DtoActualsDayTwo;
import com.example.promotion.service.ActualsService;
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
    ActualsService actualsService;

    @GetMapping("/analysisPromo")
    public ResponseEntity<?> analysisMarker() {
        List<DtoActualsPromo> list = actualsService.uploadFact();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @PostMapping("/analysisDay")
    public ResponseEntity<?> analysisDay(@RequestParam(name = "idProduct") Long idProduct, @RequestParam(name = "chainName") String chainName) {
        List<DtoActualsDayTwo> list = actualsService.uploadFactByProductAndChainName(idProduct, chainName);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
}
