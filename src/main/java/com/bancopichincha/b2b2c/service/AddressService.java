package com.bancopichincha.b2b2c.service;

import com.bancopichincha.b2b2c.service.dto.AddressDto;


public interface AddressService extends CrudService<AddressDto, Integer>{
    AddressDto findByUnique(Integer client_id);
}
