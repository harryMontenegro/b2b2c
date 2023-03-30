package com.bancopichincha.b2b2c.service;

import com.bancopichincha.b2b2c.domain.enums.DocumentType;
import com.bancopichincha.b2b2c.service.dto.ClientDto;


public interface ClientService extends CrudService<ClientDto, Integer>{
    ClientDto findByUnique(DocumentType documentType, String dni);
}
