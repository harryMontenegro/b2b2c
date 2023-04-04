package com.bancopichincha.b2b2c.service.impl.generateinformation;

import com.bancopichincha.b2b2c.domain.enums.DocumentType;
import com.bancopichincha.b2b2c.service.IGenerateDataBasicClientPorcent;
import com.bancopichincha.b2b2c.service.dto.ClientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GenerateDataBasicClientPorcentImpl implements IGenerateDataBasicClientPorcent {

    private final GenerateInformationPorcentsClientImpl generatePorcent;

    @Autowired
    public GenerateDataBasicClientPorcentImpl(GenerateInformationPorcentsClientImpl generatePorcent) {
        this.generatePorcent = generatePorcent;
    }

    @Override
    public Map<String, Map<String, Double>> getPorcentDataBasicClient(List<ClientDto> clients, Integer total) {

        Map<String, Map<String, Double>> dataReturn = new HashMap<>();
        Map<String, Double> gender = new HashMap<>();
        Map<String, Double> civilStatus = new HashMap<>();
        Map<String, Double> personType = new HashMap<>();

        clients.forEach(clientDto -> {

            if (!gender.containsKey(clientDto.getGender().toString())){

                gender.put(clientDto.getGender().toString(), generatePorcent.getPorcents(clients
                        .stream()
                        .filter(cli -> cli.getGender().equals(clientDto.getGender())).collect(Collectors.toList()), clients.size()));

                dataReturn.put("gender", gender);
            }

            if (!civilStatus.containsKey(clientDto.getCivilStatus().toString())){

                civilStatus.put(clientDto.getCivilStatus().toString(), generatePorcent.getPorcents(clients
                        .stream()
                        .filter(cli -> cli.getCivilStatus().equals(clientDto.getCivilStatus())).collect(Collectors.toList()), clients.size()));

                dataReturn.put("civilStatus", civilStatus);
            }

        });

        return dataReturn;
    }
}
