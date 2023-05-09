package com.bancopichincha.b2b2c.service.dto;

import com.bancopichincha.b2b2c.domain.enums.CivilStatus;
import com.bancopichincha.b2b2c.domain.enums.Gender;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class FilterRequest {
    private List<CivilStatus> civilStatus;
    private List<Gender> gender;
    private List<String> city;
    private DateFilterRequest dateFilter;

    public Optional<List<CivilStatus>> getOptionalCivilStatus(){
        return Optional.ofNullable(civilStatus);
    }
    public Optional<List<Gender>> getOptionalGender(){
        return Optional.ofNullable(gender);
    }
    public Optional<List<String>> getOptionalCity(){
        return Optional.ofNullable(city);
    }
    public Optional<DateFilterRequest> getOptionalDateFilter(){
        return Optional.ofNullable(dateFilter);
    }
}
