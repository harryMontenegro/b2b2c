package com.bancopichincha.b2b2c.service;

import com.bancopichincha.b2b2c.service.dto.ModuleQualificationDto;

public interface ModuleQualificationService extends CrudService<ModuleQualificationDto, Integer> {
    ModuleQualificationDto findByUnique(String grafic, Integer busisness_id);
}
