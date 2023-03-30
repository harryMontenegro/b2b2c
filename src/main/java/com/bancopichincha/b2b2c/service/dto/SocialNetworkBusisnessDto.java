package com.bancopichincha.b2b2c.service.dto;

import com.bancopichincha.b2b2c.domain.enums.SocialNetwork;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SocialNetworkBusisnessDto {

    private Integer socialnetwork_busisness_id;
    private Integer busisness_id;
    private SocialNetwork socialNetwork;
    private String name;

}
