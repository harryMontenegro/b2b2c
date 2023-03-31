package com.bancopichincha.b2b2c.service;


import com.bancopichincha.b2b2c.service.dto.ResponseInformationDto;

public interface IGenerateInformationBusisness {
    ResponseInformationDto generateInformation(Integer busisness_id);
}
