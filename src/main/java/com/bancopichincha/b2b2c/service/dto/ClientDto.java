package com.bancopichincha.b2b2c.service.dto;

import com.bancopichincha.b2b2c.domain.enums.CivilStatus;
import com.bancopichincha.b2b2c.domain.enums.DocumentType;
import com.bancopichincha.b2b2c.domain.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class ClientDto {
    private Integer client_id;
    private String name;
    private String lastName;
    private DocumentType documentType;
    private String dni;
    private CivilStatus civilStatus;
    private Gender gender;
    private LocalDate birthdate;
}
