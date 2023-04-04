package com.bancopichincha.b2b2c.service;

import com.bancopichincha.b2b2c.service.dto.ClientDto;

import java.util.List;

public interface IGenerateInformationPorcentClient {
    Double getPorcents(List<ClientDto> clientDto, Integer total);

}
