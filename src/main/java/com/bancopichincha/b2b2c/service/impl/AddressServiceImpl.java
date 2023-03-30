package com.bancopichincha.b2b2c.service.impl;

import com.bancopichincha.b2b2c.domain.Address;
import com.bancopichincha.b2b2c.repository.AddressRepository;
import com.bancopichincha.b2b2c.service.AddressService;
import com.bancopichincha.b2b2c.service.dto.AddressDto;
import com.bancopichincha.b2b2c.service.dto.PaginableDTO;
import com.bancopichincha.b2b2c.service.mapper.MapperObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository repository;
    private final MapperObject mapper;

    @Autowired
    public AddressServiceImpl(AddressRepository repository, MapperObject mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public AddressDto create(AddressDto addressDto) {

        AddressDto unique = mapper.map().convertValue(findByUnique(addressDto.getClient_id()), AddressDto.class);

        if(unique != null){
            addressDto.setAddress_id(unique.getAddress_id());
        }
        return mapper.map().convertValue(repository.save(mapper.map().convertValue(addressDto, Address.class)), AddressDto.class);
    }

    @Override
    public List<AddressDto> list(PaginableDTO pageable) {
        return Optional.of(repository.findAll(PageRequest.of(pageable.getPagina(), pageable.getCantidad())))
                .get()
                .get()
                .map(c -> mapper.map().convertValue(c, AddressDto.class)).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public AddressDto update(AddressDto addressDto) {
        return create(addressDto);
    }

    @Override
    public Optional<AddressDto> findOneById(Integer id) {
        return Optional.of(Optional.of(mapper.map().convertValue(repository.findById(id).orElse(new Address()), AddressDto.class))).orElse(null);
    }

    @Override
    public AddressDto findByUnique(Integer client_id) {
        return mapper.map().convertValue(repository.findByUnique(client_id), AddressDto.class);
    }
}
