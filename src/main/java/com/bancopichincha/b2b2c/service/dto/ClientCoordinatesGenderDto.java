package com.bancopichincha.b2b2c.service.dto;

import com.bancopichincha.b2b2c.domain.enums.Gender;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ClientCoordinatesGenderDto {
    private Gender gender;
    private String latitude;
    private String longitude;
}
