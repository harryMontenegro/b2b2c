package com.bancopichincha.b2b2c.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DateFilterRequest {
    private LocalDate dateFrom;
    private LocalDate dateTo;

    @Override
    public String toString() {
        return  "dateFrom=" + dateFrom +
                ", dateTo=" + dateTo;
    }
}
