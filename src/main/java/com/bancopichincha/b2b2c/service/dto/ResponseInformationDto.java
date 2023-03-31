package com.bancopichincha.b2b2c.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseInformationDto {
    private Integer totalClient;
    private Double porcentWomen;
    private Double porcentMen;
    private Double porcentMarried;
    private Double porcentSingle;
    private PersonTypeDto personTypeDto;
}
