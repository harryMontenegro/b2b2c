package com.bancopichincha.b2b2c.controller;

import com.bancopichincha.b2b2c.service.BusisnessService;
import com.bancopichincha.b2b2c.service.dto.BusisnessDto;
import com.bancopichincha.b2b2c.service.dto.BusisnessSingleDto;
import com.bancopichincha.b2b2c.service.dto.PaginableDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/busisness")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:80", "http://localhost", "http://18.191.165.97/"})
public class BusisnessController {

    private final BusisnessService service;

    @Autowired
    public BusisnessController(BusisnessService service){
        this.service = service;
    }

    @PostMapping("/findAll")
    public ResponseEntity<?> findAllPageable(@RequestBody PaginableDTO paginableDTO){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAllSingle(paginableDTO));
    }

    @PostMapping("/fullFindAll")
    public ResponseEntity<?> findAllSinglePageable(@RequestBody PaginableDTO paginableDTO){
        return ResponseEntity.status(HttpStatus.OK).body(service.list(paginableDTO));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findOneById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findOneById(id).orElse(null));
    }

    @GetMapping("/unique/{ruc}")
    public ResponseEntity<?> findByUnique(@PathVariable String ruc)  {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByUnique(ruc));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody BusisnessDto BusisnessDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(BusisnessDto));
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody BusisnessDto BusisnessDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.update(BusisnessDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> findOneByName(@PathVariable String name){
        BusisnessSingleDto data = service.findByName(name);
        return data == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(null) : ResponseEntity.status(HttpStatus.OK).body(data);
    }
}
