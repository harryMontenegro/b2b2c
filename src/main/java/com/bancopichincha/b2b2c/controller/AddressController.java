package com.bancopichincha.b2b2c.controller;

import com.bancopichincha.b2b2c.service.AddressService;
import com.bancopichincha.b2b2c.service.dto.AddressDto;
import com.bancopichincha.b2b2c.service.dto.PaginableDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/address")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:80", "http://localhost", "http://18.191.165.97", "http://18.191.165.97:80", "http://iotemp.ml"})
public class AddressController {

    private final AddressService service;

    @Autowired
    public AddressController(AddressService service){
        this.service = service;
    }

    @PostMapping("/findAll")
    public ResponseEntity<?> findAllPageable(@RequestBody PaginableDTO paginableDTO){
        return ResponseEntity.status(HttpStatus.OK).body(service.list(paginableDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOneById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findOneById(id).orElse(null));
    }

    @GetMapping("/unique/{client_id}")
    public ResponseEntity<?> findByUnique(@PathVariable Integer client_id)  {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByUnique(client_id));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody AddressDto addressDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(addressDto));
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody AddressDto addressDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.update(addressDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
