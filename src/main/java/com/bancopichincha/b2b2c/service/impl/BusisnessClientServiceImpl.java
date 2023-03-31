package com.bancopichincha.b2b2c.service.impl;

import com.bancopichincha.b2b2c.domain.BusisnessClient;
import com.bancopichincha.b2b2c.repository.BusisnessClientRepository;
import com.bancopichincha.b2b2c.service.BusisnessClientService;
import com.bancopichincha.b2b2c.service.dto.BusisnessClientDto;
import com.bancopichincha.b2b2c.service.dto.PaginableDTO;
import com.bancopichincha.b2b2c.service.mapper.MapperObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BusisnessClientServiceImpl implements BusisnessClientService {

    private final BusisnessClientRepository repository;
    private final MapperObject mapper;

    @Autowired
    public BusisnessClientServiceImpl(BusisnessClientRepository repository, MapperObject mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public BusisnessClientDto create(BusisnessClientDto busisnessClientDto) {

        BusisnessClientDto unique = mapper.map().convertValue(findByUnique(busisnessClientDto.getBusisness_id(), busisnessClientDto.getClient_id()), BusisnessClientDto.class);

        if(unique != null){
            busisnessClientDto.setBusisness_client_id(unique.getBusisness_client_id());
        }
        return mapper.map().convertValue(repository.save(mapper.map().convertValue(busisnessClientDto, BusisnessClient.class)), BusisnessClientDto.class);
    }

    @Override
    public List<BusisnessClientDto> list(PaginableDTO pageable) {
        return Optional.of(repository.findAll(PageRequest.of(pageable.getPagina(), pageable.getCantidad())))
                .get()
                .get()
                .map(c -> mapper.map().convertValue(c, BusisnessClientDto.class)).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public BusisnessClientDto update(BusisnessClientDto busisnessClient) {
        return create(busisnessClient);
    }

    @Override
    public Optional<BusisnessClientDto> findOneById(Integer id) {
        return Optional.of(Optional.of(mapper.map().convertValue(repository.findById(id).orElse(new BusisnessClient()), BusisnessClientDto.class))).orElse(null);
    }

    @Override
    public BusisnessClientDto findByUnique(Integer busisness_id, Integer client_id) {
        return mapper.map().convertValue(repository.findByUnique(busisness_id, client_id), BusisnessClientDto.class);
    }

    @Override
    public List<BusisnessClientDto> findByBusisness(Integer busisness_id) {
        return List.of(mapper.map().convertValue(repository.findByBusisness(busisness_id), BusisnessClientDto[].class));
    }
}
