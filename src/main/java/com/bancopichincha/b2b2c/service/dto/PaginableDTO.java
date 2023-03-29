package com.bancopichincha.b2b2c.service.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Negative;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PaginableDTO {

    private Integer pagina;
    private Integer cantidad;
}
