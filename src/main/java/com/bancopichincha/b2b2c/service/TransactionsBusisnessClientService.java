package com.bancopichincha.b2b2c.service;

import com.bancopichincha.b2b2c.service.dto.TransactionsBusisnessClientDto;

import java.util.List;

public interface TransactionsBusisnessClientService extends CrudService<TransactionsBusisnessClientDto, Integer>, ITransactionsGraphicClientService{
    List<TransactionsBusisnessClientDto> findByBusisness(Integer busisness_id);
}
