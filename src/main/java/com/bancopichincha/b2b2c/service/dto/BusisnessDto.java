package com.bancopichincha.b2b2c.service.dto;

import com.bancopichincha.b2b2c.domain.BusisnessClient;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BusisnessDto {
    private Integer busisness_id;
    private String name;
    private String ruc;
    private List<BusisnessClient> busisnessClient;
}
