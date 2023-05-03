package com.bancopichincha.b2b2c.service.impl;


import com.bancopichincha.b2b2c.domain.ModuleQualification;
import com.bancopichincha.b2b2c.repository.ModuleQualificationRepository;
import com.bancopichincha.b2b2c.service.ModuleQualificationService;
import com.bancopichincha.b2b2c.service.dto.ModuleQualificationDto;
import com.bancopichincha.b2b2c.service.dto.PaginableDTO;
import com.bancopichincha.b2b2c.service.mapper.MapperObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ModuleQualificationServiceImpl implements ModuleQualificationService {

    private final ModuleQualificationRepository repository;
    private final MapperObject mapper;

    @Autowired
    public ModuleQualificationServiceImpl(ModuleQualificationRepository repository, MapperObject mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ModuleQualificationDto create(ModuleQualificationDto moduleQualificationDto) {

        ModuleQualificationDto unique = findByUnique(moduleQualificationDto.getGraphic(), moduleQualificationDto.getBusisness_id());
        if(unique != null){
            moduleQualificationDto.setModulequalification_id(unique.getModulequalification_id());
        }

        return mapper.map().convertValue(repository.save(mapper.map().convertValue(moduleQualificationDto, ModuleQualification.class)), ModuleQualificationDto.class);
    }

    @Override
    public List<ModuleQualificationDto> list(PaginableDTO pageable) {
        return Optional.of(repository.findAll(PageRequest.of(pageable.getPagina(), pageable.getCantidad())))
                .get()
                .get()
                .map(c -> mapper.map().convertValue(c, ModuleQualificationDto.class)).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public ModuleQualificationDto update(ModuleQualificationDto moduleQualificationDto) {
        return create(moduleQualificationDto);
    }

    @Override
    public Optional<ModuleQualificationDto> findOneById(Integer id) {
        return Optional.of(Optional.of(mapper.map().convertValue(repository.findById(id).orElse(new ModuleQualification()), ModuleQualificationDto.class))).orElse(null);
    }

    @Override
    public ModuleQualificationDto findByUnique(String grafic, Integer busisness_id) {
        return mapper.map().convertValue(repository.findByUnique(grafic, busisness_id), ModuleQualificationDto.class);
    }
}
