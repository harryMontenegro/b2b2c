package com.bancopichincha.b2b2c.service.impl;

import static com.bancopichincha.b2b2c.domain.enums.DocumentType.*;

import static com.bancopichincha.b2b2c.domain.enums.CivilStatus.*;
import com.bancopichincha.b2b2c.domain.enums.Gender;
import com.bancopichincha.b2b2c.service.BusisnessClientService;
import com.bancopichincha.b2b2c.service.IGenerateInformationBusisness;
import com.bancopichincha.b2b2c.service.IGenerateInformationAllClient;
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
    private final IGenerateInformationAllClient clientService;

    @Autowired
    public GenerateInformationBusisnessImpl(BusisnessClientService busisnessService, IGenerateInformationAllClient clientService) {
        this.busisnessService = busisnessService;
        this.clientService = clientService;
    }

    @Override
    public ResponseInformationDto generateInformation(Integer busisness_id) {


        ResponseInformationDto response = new ResponseInformationDto();

        List<BusisnessClientDto> informationBusisness = busisnessService.findByBusisness(busisness_id);

        List<ClientDto> clients = informationBusisness
                .stream()
                .flatMap(client -> Stream.of(client.getClient())).collect(Collectors.toList());

        List<ClientDto> clientWomen = clients
                .parallelStream()
                .filter(client -> client.getGender().equals(Gender.FEMALE))
                .collect(Collectors.toList());

        List<ClientDto> clientMen = clients
                .stream()
                .filter(client -> client.getGender().equals(Gender.MALE))
                .collect(Collectors.toList());

        List<ClientDto> clientNat = clients
                .stream()
                .filter(client -> client.getDocumentType().equals(CC) || client.getDocumentType().equals(TI) || client.getDocumentType().equals(PASS))
                .collect(Collectors.toList());

        List<ClientDto> clientJur = clients
                .stream()
                .filter(client -> client.getDocumentType().equals(RUC))
                .collect(Collectors.toList());

        List<ClientDto> clientMarried = clients
                .stream()
                .filter(client -> client.getCivilStatus().equals(MARRIED))
                .collect(Collectors.toList());

        List<ClientDto> clientSingle = clients
                .stream()
                .filter(client -> client.getCivilStatus().equals(SINGLE) || client.getCivilStatus().equals(CONCUBINAGE))
                .collect(Collectors.toList());


        response.setTotalClient(clients.size());
        response.setPorcentWomen(clientService.getPorcents(clientWomen, clients.size()));
        response.setPorcentMen(clientService.getPorcents(clientMen, clients.size()));
        response.setPorcentMarried(clientService.getPorcents(clientMarried, clients.size()));
        response.setPorcentSingle(clientService.getPorcents(clientSingle, clients.size()));

        PersonTypeDto personTypeDto = new PersonTypeDto();
        personTypeDto.setPorcentNatutal(clientService.getPorcents(clientNat, clients.size()));
        personTypeDto.setPorcentLegal(clientService.getPorcents(clientJur, clients.size()));
        response.setPersonTypeDto(personTypeDto);

        return response;
    }
}
