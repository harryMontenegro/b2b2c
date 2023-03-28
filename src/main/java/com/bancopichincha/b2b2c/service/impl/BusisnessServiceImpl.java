package com.bancopichincha.b2b2c.service.impl;

import com.bancopichincha.b2b2c.domain.Busisness;
import com.bancopichincha.b2b2c.repository.BusisnessRepository;
import com.bancopichincha.b2b2c.service.BusisnessService;
import com.bancopichincha.b2b2c.service.dto.BusisnessDto;
import com.bancopichincha.b2b2c.service.dto.PaginableDTO;
import com.bancopichincha.b2b2c.service.mapper.MapperObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BusisnessServiceImpl implements BusisnessService {

    private final BusisnessRepository repository;
    private final MapperObject mapper;

    @Autowired
    public BusisnessServiceImpl(BusisnessRepository repository, MapperObject mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public BusisnessDto create(BusisnessDto busisnessDto) {

        BusisnessDto unique = mapper.mappear().convertValue(findByUnique(busisnessDto.getRuc()), BusisnessDto.class);

        if(unique != null){
            busisnessDto.setBusisness_id(unique.getBusisness_id());
        }
        return mapper.mappear().convertValue(repository.save(mapper.mappear().convertValue(busisnessDto, Busisness.class)), BusisnessDto.class);
    }

    @Override
    public List<BusisnessDto> list(PaginableDTO pageable) {
        return Optional.of(repository.findAll(PageRequest.of(pageable.getPagina(), pageable.getCantidad())))
                .get()
                .get()
                .map(c -> mapper.mappear().convertValue(c, BusisnessDto.class)).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public BusisnessDto update(BusisnessDto busisnessDto) {
        return create(busisnessDto);
    }

    @Override
    public Optional<BusisnessDto> findOneById(Integer id) {
        return Optional.of(Optional.of(mapper.mappear().convertValue(repository.findById(id), BusisnessDto.class))).orElse(null);
    }

    @Override
    public BusisnessDto findByUnique(String ruc) {
        return mapper.mappear().convertValue(repository.findByRuc(ruc), BusisnessDto.class);
    }
}
