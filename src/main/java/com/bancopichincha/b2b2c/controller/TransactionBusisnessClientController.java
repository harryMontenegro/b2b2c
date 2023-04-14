package com.bancopichincha.b2b2c.controller;


import com.bancopichincha.b2b2c.service.IBusisnessClientPoliticalDivision;
import com.bancopichincha.b2b2c.service.TransactionsBusisnessClientService;
import com.bancopichincha.b2b2c.service.dto.PaginableDTO;
import com.bancopichincha.b2b2c.service.dto.PoliticalDivisionDto;
import com.bancopichincha.b2b2c.service.dto.TransactionsBusisnessClientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/transactionsBusisnessClient")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:80", "http://localhost"})
public class TransactionBusisnessClientController {

    private final TransactionsBusisnessClientService service;
    private final IBusisnessClientPoliticalDivision servicePoliticalDivision;

    @Autowired
    public TransactionBusisnessClientController(TransactionsBusisnessClientService service, IBusisnessClientPoliticalDivision servicePoliticalDivision){
        this.service = service;
        this.servicePoliticalDivision = servicePoliticalDivision;
    }

    @PostMapping("/findAll")
    public ResponseEntity<?> findAllPageable(@RequestBody PaginableDTO paginableDTO){
        return ResponseEntity.status(HttpStatus.OK).body(service.list(paginableDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOneById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findOneById(id).orElse(null));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody TransactionsBusisnessClientDto transactionBusisnessClientDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(transactionBusisnessClientDto));
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody TransactionsBusisnessClientDto transactionBusisnessClientDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.update(transactionBusisnessClientDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/busisness/graphic/{busisness_id}")
    public ResponseEntity<?> graphicTransaction(@PathVariable Integer busisness_id){
        return ResponseEntity.status(HttpStatus.OK).body(service.graphTransactions(busisness_id));
    }

    @GetMapping("/politicalDivision/busisness/graphic/{busisness_id}")
    public ResponseEntity<?> politicalDivisiongraphicTransaction(@PathVariable Integer busisness_id){
        List<PoliticalDivisionDto> data = servicePoliticalDivision.calculatedClientByPoliticalDivision(busisness_id);
        return data.size() > 0 ? ResponseEntity.status(HttpStatus.OK).body(data) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(data);
    }
}
