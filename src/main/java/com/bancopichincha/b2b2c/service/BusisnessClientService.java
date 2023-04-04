package com.bancopichincha.b2b2c.service;


import com.bancopichincha.b2b2c.service.dto.BusisnessClientDto;
import com.bancopichincha.b2b2c.service.dto.ClientCoordinatesGenderDto;

import java.util.List;


public interface BusisnessClientService extends CrudService<BusisnessClientDto, Integer>{
    BusisnessClientDto findByUnique(Integer busisness_id, Integer client_id);
    List<BusisnessClientDto> findByBusisness(Integer busisness_id);
    List<ClientCoordinatesGenderDto> findByBusisnessClientCordinates(Integer busisness_id);
}
