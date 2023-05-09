package com.bancopichincha.b2b2c.service.dto;

import com.bancopichincha.b2b2c.domain.enums.CivilStatus;
import com.bancopichincha.b2b2c.domain.enums.DocumentType;
import com.bancopichincha.b2b2c.domain.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Optional;


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
    private AddressDto address;

    public Optional<AddressDto> getAddressOptional() {
        return Optional.ofNullable(address);
    }

    @Override
    public String toString() {
        return "ClientDto{" +
                "name='" + name + '\'' +
                ", civilStatus=" + civilStatus +
                ", gender=" + gender +
                '}';
    }
}
