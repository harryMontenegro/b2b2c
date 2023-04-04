package com.bancopichincha.b2b2c.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ResponseInformationDto {
    private Integer totalClient;
    private PersonTypeDto personTypeDto;
    private Map<String, Map<String, Double>> dataClient;
}
