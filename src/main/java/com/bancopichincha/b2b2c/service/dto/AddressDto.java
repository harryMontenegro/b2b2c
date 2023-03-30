package com.bancopichincha.b2b2c.service.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AddressDto {

    private Integer address_id;
    private String addres;
    private String latitude;
    private String longitude;
    private Integer client_id;
}
