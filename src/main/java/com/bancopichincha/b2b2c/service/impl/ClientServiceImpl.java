package com.bancopichincha.b2b2c.service.impl;

import com.bancopichincha.b2b2c.domain.Client;
import com.bancopichincha.b2b2c.domain.enums.DocumentType;
import com.bancopichincha.b2b2c.repository.ClientRepository;
import com.bancopichincha.b2b2c.service.ClientService;
import com.bancopichincha.b2b2c.service.dto.ClientDto;
import com.bancopichincha.b2b2c.service.dto.PaginableDTO;
import com.bancopichincha.b2b2c.service.mapper.MapperObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;
    private final MapperObject mapper;

    @Autowired
    public ClientServiceImpl(ClientRepository repository, MapperObject mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ClientDto create(ClientDto clientDto) {


        ClientDto unique = mapper.map().convertValue(findByUnique(clientDto.getDocumentType(), clientDto.getDni()), ClientDto.class);

        if(unique != null){
            clientDto.setClient_id(unique.getClient_id());
        }
        return mapper.map().convertValue(repository.save(mapper.map().convertValue(clientDto, Client.class)), ClientDto.class);
    }

    @Override
    public List<ClientDto> list(PaginableDTO pageable) {
        return Optional.of(repository.findAll(PageRequest.of(pageable.getPagina(), pageable.getCantidad())))
                .get()
                .get()
                .map(c -> mapper.map().convertValue(c, ClientDto.class)).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public ClientDto update(ClientDto busisnessClient) {
        return create(busisnessClient);
    }

    @Override
    public Optional<ClientDto> findOneById(Integer id) {
        return Optional.of(Optional.of(mapper.map().convertValue(repository.findById(id).orElse(new Client()), ClientDto.class))).orElse(null);
    }

    @Override
    public ClientDto findByUnique(DocumentType documentType, String dni) {
        return mapper.map().convertValue(repository.findByUnique(documentType, dni), ClientDto.class);
    }
}
