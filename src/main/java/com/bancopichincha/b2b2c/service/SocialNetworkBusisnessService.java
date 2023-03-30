package com.bancopichincha.b2b2c.service;


import com.bancopichincha.b2b2c.domain.enums.SocialNetwork;
import com.bancopichincha.b2b2c.service.dto.SocialNetworkBusisnessDto;


public interface SocialNetworkBusisnessService extends CrudService<SocialNetworkBusisnessDto, Integer>{
    SocialNetworkBusisnessDto findByUnique(SocialNetwork socialNetwork, String name, Integer busisness_id);
}
