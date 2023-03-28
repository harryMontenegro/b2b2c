package com.bancopichincha.b2b2c.service;

import com.bancopichincha.b2b2c.service.dto.BusisnessDto;


public interface BusisnessService extends CrudService<BusisnessDto, Integer>{
    BusisnessDto findByUnique(String ruc);

}
