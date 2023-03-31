package com.bancopichincha.b2b2c.controller;

import com.bancopichincha.b2b2c.service.BusisnessClientService;
import com.bancopichincha.b2b2c.service.dto.BusisnessClientDto;
import com.bancopichincha.b2b2c.service.dto.PaginableDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("api/v1/busisnessClient")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:80", "http://localhost"})
public class BusisnessClientController {

    private final BusisnessClientService service;

    @Autowired
    public BusisnessClientController(BusisnessClientService service){
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

    @GetMapping("/unique/{busisness_id}/{client_id}")
    public ResponseEntity<?> findByUnique(@PathVariable Integer busisness_id, @PathVariable Integer client_id)  {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByUnique(busisness_id, client_id));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody BusisnessClientDto busisnessClientDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(busisnessClientDto));
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody BusisnessClientDto busisnessClientDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.update(busisnessClientDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/busisness/{busisness_id}")
    public ResponseEntity<?> findByBusisness(@PathVariable Integer busisness_id)  {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByBusisness(busisness_id)
                .stream()
                .flatMap(client -> Stream.of(client.getClient())).collect(Collectors.toList()));
    }
}
