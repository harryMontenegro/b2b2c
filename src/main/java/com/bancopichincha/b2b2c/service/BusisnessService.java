package com.bancopichincha.b2b2c.service;

import com.bancopichincha.b2b2c.service.dto.BusisnessDto;
import com.bancopichincha.b2b2c.service.dto.BusisnessSingleDto;
import com.bancopichincha.b2b2c.service.dto.PaginableDTO;

import java.util.List;
import java.util.Optional;


public interface BusisnessService extends CrudService<BusisnessDto, Integer>{
    BusisnessDto findByUnique(String ruc);
    List<BusisnessSingleDto> findAllSingle(PaginableDTO pageable);

    BusisnessSingleDto findByName(String name);

}
