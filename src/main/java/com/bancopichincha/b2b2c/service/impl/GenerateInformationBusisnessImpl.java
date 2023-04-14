package com.bancopichincha.b2b2c.service.impl;

import static com.bancopichincha.b2b2c.domain.enums.DocumentType.*;

import com.bancopichincha.b2b2c.service.BusisnessClientService;
import com.bancopichincha.b2b2c.service.IGenerateDataBasicClientPorcent;
import com.bancopichincha.b2b2c.service.IGenerateInformationBusisness;
import com.bancopichincha.b2b2c.service.IGenerateInformationPorcentClient;
import com.bancopichincha.b2b2c.service.dto.BusisnessClientDto;
import com.bancopichincha.b2b2c.service.dto.ClientDto;
import com.bancopichincha.b2b2c.service.dto.PersonTypeDto;
import com.bancopichincha.b2b2c.service.dto.ResponseInformationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GenerateInformationBusisnessImpl implements IGenerateInformationBusisness {

    private final BusisnessClientService busisnessService;
    private final IGenerateInformationPorcentClient clientService;

    private final IGenerateDataBasicClientPorcent dataBasicService;

    @Autowired
    public GenerateInformationBusisnessImpl(BusisnessClientService busisnessService, IGenerateInformationPorcentClient clientService, IGenerateDataBasicClientPorcent dataBasicService) {
        this.busisnessService = busisnessService;
        this.clientService = clientService;
        this.dataBasicService = dataBasicService;
    }

    @Override
    public ResponseInformationDto generateInformation(Integer busisness_id) {


        ResponseInformationDto response = new ResponseInformationDto();

        List<BusisnessClientDto> informationBusisness = busisnessService.findByBusisness(busisness_id);

        List<ClientDto> clients = informationBusisness
                .stream()
                .flatMap(client -> Stream.of(client.getClient())).collect(Collectors.toList());

        List<ClientDto> clientNat = clients
                .stream()
                .filter(client -> client.getDocumentType().equals(CC) ||client.getDocumentType().equals(PASS))
                .collect(Collectors.toList());

        List<ClientDto> clientJur = clients
                .stream()
                .filter(client -> client.getDocumentType().equals(RUC))
                .collect(Collectors.toList());

        response.setTotalClient(clients.size());

        PersonTypeDto personTypeDto = new PersonTypeDto();
        personTypeDto.setPorcentNatutal(clientService.getPorcents(clientNat, clients.size()));
        personTypeDto.setPorcentLegal(clientService.getPorcents(clientJur, clients.size()));
        response.setPersonTypeDto(personTypeDto);

        response.setDataClient(dataBasicService.getPorcentDataBasicClient(clients, clients.size()));

        return response;
    }
}
