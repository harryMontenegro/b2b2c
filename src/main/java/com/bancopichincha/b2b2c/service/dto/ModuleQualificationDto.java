package com.bancopichincha.b2b2c.service.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class ModuleQualificationDto {
    private Integer modulequalification_id;
    private String graphic;
    private Integer busisness_id;
    private Integer qualification;
    private LocalDate qualificationDate;
}
