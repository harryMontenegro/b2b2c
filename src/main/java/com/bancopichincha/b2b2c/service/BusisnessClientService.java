package com.bancopichincha.b2b2c.service;


import com.bancopichincha.b2b2c.service.dto.BusisnessClientDto;


public interface BusisnessClientService extends CrudService<BusisnessClientDto, Integer>{
    BusisnessClientDto findByUnique(Integer busisness_id, Integer client_id);
}
