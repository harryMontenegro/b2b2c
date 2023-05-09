package com.bancopichincha.b2b2c.controller;

import com.bancopichincha.b2b2c.domain.enums.SocialNetwork;
import com.bancopichincha.b2b2c.service.SocialNetworkBusisnessService;
import com.bancopichincha.b2b2c.service.dto.PaginableDTO;
import com.bancopichincha.b2b2c.service.dto.SocialNetworkBusisnessDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/socialNetworkBusisness")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:80", "http://localhost", "http://18.191.165.97", "http://18.191.165.97:80", "http://iotemp.ml","https://iotemp.ml"})
public class SocialNetworkBusisnessController {

    private final SocialNetworkBusisnessService service;

    @Autowired
    public SocialNetworkBusisnessController(SocialNetworkBusisnessService service){
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

    @GetMapping("/unique/{busisness_id}/{socialNetwork}/{name}")
    public ResponseEntity<?> findByUnique(@PathVariable Integer busisness_id, @PathVariable SocialNetwork socialNetwork, @PathVariable String name)  {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByUnique(socialNetwork, name, busisness_id));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody SocialNetworkBusisnessDto socialNetworkBusisnessDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(socialNetworkBusisnessDto));
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody SocialNetworkBusisnessDto socialNetworkBusisnessDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.update(socialNetworkBusisnessDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
