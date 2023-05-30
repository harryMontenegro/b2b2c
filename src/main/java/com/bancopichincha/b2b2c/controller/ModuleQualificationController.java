package com.bancopichincha.b2b2c.controller;

import com.bancopichincha.b2b2c.service.ModuleQualificationService;
import com.bancopichincha.b2b2c.service.dto.ModuleQualificationDto;
import com.bancopichincha.b2b2c.service.dto.PaginableDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/moduleQualification")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:80", "http://localhost", "http://18.191.165.97", "http://18.191.165.97:80", "http://iotemp.ml","https://iotemp.ml", "https://dev.gabrielg4t.xyz"})
public class ModuleQualificationController {

    private final ModuleQualificationService service;

    @Autowired
    public ModuleQualificationController(ModuleQualificationService service){
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

    @GetMapping("/unique/graphic/{graphic}/busisness/{busisness_id}")
    public ResponseEntity<?> findByUnique(@PathVariable String graphic, @PathVariable Integer busisness_id)  {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByUnique(graphic, busisness_id));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ModuleQualificationDto moduleQualificationDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(moduleQualificationDto));
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody ModuleQualificationDto moduleQualificationDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.update(moduleQualificationDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
