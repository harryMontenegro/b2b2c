package com.bancopichincha.b2b2c.controller;

import com.bancopichincha.b2b2c.domain.enums.DocumentType;
import com.bancopichincha.b2b2c.service.ClientService;
import com.bancopichincha.b2b2c.service.dto.ClientDto;
import com.bancopichincha.b2b2c.service.dto.PaginableDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/client")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:80", "http://localhost", "http://18.191.165.97/"})
public class ClientController {

    private final ClientService service;

    @Autowired
    public ClientController(ClientService service){
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

    @GetMapping("/unique/{documentType}/{dni}")
    public ResponseEntity<?> findByUnique(@PathVariable DocumentType documentType, @PathVariable String dni)  {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByUnique(documentType, dni));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ClientDto clientDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(clientDto));
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody ClientDto clientDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.update(clientDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
