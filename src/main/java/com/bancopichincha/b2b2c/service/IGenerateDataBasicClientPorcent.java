package com.bancopichincha.b2b2c.service;

import com.bancopichincha.b2b2c.service.dto.ClientDto;

import java.util.List;
import java.util.Map;

public interface IGenerateDataBasicClientPorcent {
    Map<String, Map<String, Double>> getPorcentDataBasicClient(List<ClientDto> clients, Integer total);
}
