package com.bancopichincha.b2b2c.service;

import com.bancopichincha.b2b2c.service.dto.PoliticalDivisionDto;

import java.util.List;

public interface IBusisnessClientPoliticalDivision {
    List<PoliticalDivisionDto> calculatedClientByPoliticalDivision(Integer busisness_id);
}
