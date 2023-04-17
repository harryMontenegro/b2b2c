package com.bancopichincha.b2b2c.controller;

import com.bancopichincha.b2b2c.service.IGenerateInformationBusisness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/generateInformation")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:80", "http://localhost", "http://18.191.165.97", "http://18.191.165.97:80"})
public class GenerateInformationController {

    private final IGenerateInformationBusisness service;

    @Autowired
    public GenerateInformationController(IGenerateInformationBusisness service){
        this.service = service;
    }

    @GetMapping("/busisness/{busisness_id}")
    public ResponseEntity<?> findOneByName(@PathVariable Integer busisness_id){
        return ResponseEntity.status(HttpStatus.OK).body(service.generateInformation(busisness_id));
    }

}
