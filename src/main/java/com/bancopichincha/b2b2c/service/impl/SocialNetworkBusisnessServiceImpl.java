package com.bancopichincha.b2b2c.service.impl;

import com.bancopichincha.b2b2c.domain.SocialNetworkBusisness;
import com.bancopichincha.b2b2c.domain.enums.SocialNetwork;
import com.bancopichincha.b2b2c.repository.SocialNetworkBusisnessRepository;
import com.bancopichincha.b2b2c.service.SocialNetworkBusisnessService;
import com.bancopichincha.b2b2c.service.dto.PaginableDTO;
import com.bancopichincha.b2b2c.service.dto.SocialNetworkBusisnessDto;
import com.bancopichincha.b2b2c.service.mapper.MapperObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SocialNetworkBusisnessServiceImpl implements SocialNetworkBusisnessService {

    private final SocialNetworkBusisnessRepository repository;
    private final MapperObject mapper;

    @Autowired
    public SocialNetworkBusisnessServiceImpl(SocialNetworkBusisnessRepository repository, MapperObject mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public SocialNetworkBusisnessDto create(SocialNetworkBusisnessDto socialNetworkBusisnessDto) {


        SocialNetworkBusisnessDto unique = mapper.map().convertValue(findByUnique(socialNetworkBusisnessDto.getSocialNetwork(), socialNetworkBusisnessDto.getName(), socialNetworkBusisnessDto.getBusisness_id()), SocialNetworkBusisnessDto.class);

        if(unique != null){
            socialNetworkBusisnessDto.setSocialnetwork_busisness_id(unique.getSocialnetwork_busisness_id());
        }
        return mapper.map().convertValue(repository.save(mapper.map().convertValue(socialNetworkBusisnessDto, SocialNetworkBusisness.class)), SocialNetworkBusisnessDto.class);
    }

    @Override
    public List<SocialNetworkBusisnessDto> list(PaginableDTO pageable) {
        return Optional.of(repository.findAll(PageRequest.of(pageable.getPagina(), pageable.getCantidad())))
                .get()
                .get()
                .map(c -> mapper.map().convertValue(c, SocialNetworkBusisnessDto.class)).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public SocialNetworkBusisnessDto update(SocialNetworkBusisnessDto busisnessClient) {
        return create(busisnessClient);
    }

    @Override
    public Optional<SocialNetworkBusisnessDto> findOneById(Integer id) {
        return Optional.of(Optional.of(mapper.map().convertValue(repository.findById(id).orElse(new SocialNetworkBusisness()), SocialNetworkBusisnessDto.class))).orElse(null);
    }

    @Override
    public SocialNetworkBusisnessDto findByUnique(SocialNetwork socialNetwork, String name, Integer busisness_id) {
        return mapper.map().convertValue(repository.findByUnique(socialNetwork, name, busisness_id), SocialNetworkBusisnessDto.class);
    }
}
