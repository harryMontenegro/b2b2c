package com.bancopichincha.b2b2c.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PoliticalDivisionDto {
    private String city;
    private Long value;
}
