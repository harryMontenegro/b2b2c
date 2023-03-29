package com.bancopichincha.b2b2c.controller;

import com.bancopichincha.b2b2c.service.BusisnessService;
import com.bancopichincha.b2b2c.service.dto.BusisnessDto;
import com.bancopichincha.b2b2c.service.dto.PaginableDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/busisness")
public class BusisnessController {

    private final BusisnessService service;

    @Autowired
    public BusisnessController(BusisnessService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> findAllPageable(@Valid @RequestBody PaginableDTO paginableDTO){
        return ResponseEntity.status(HttpStatus.FOUND).body(service.list(paginableDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOneById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.FOUND).body(service.findOneById(id));
    }

    @GetMapping("/unico/{ruc}")
    public ResponseEntity<?> findByUnique(@PathVariable String ruc)  {
        return ResponseEntity.status(HttpStatus.FOUND).body(service.findByUnique(ruc));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody BusisnessDto BusisnessDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(BusisnessDto));
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody BusisnessDto BusisnessDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.update(BusisnessDto));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
