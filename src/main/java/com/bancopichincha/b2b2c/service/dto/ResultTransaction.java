package com.bancopichincha.b2b2c.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ResultTransaction {
    private String year;
    private List<Map<String, Long>> data;

    public ResultTransaction() {
        System.out.println("year = " + year);
        this.data = new ArrayList<>();
    }
}
