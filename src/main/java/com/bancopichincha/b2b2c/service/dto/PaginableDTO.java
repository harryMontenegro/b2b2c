package com.bancopichincha.b2b2c.service.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Negative;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PaginableDTO {
    @NotNull(message = "Pagina no puede ser null")
    @Negative(message = "Numero de paginas debe ser mayor a 0")
    private Integer pagina;
    @Negative(message = "Numero de paginas debe ser mayor a 0")
    @NotNull(message = "Cantidad no puede ser null")
    private Integer cantidad;
}
