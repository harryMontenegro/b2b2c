package com.bancopichincha.b2b2c.service.impl.graphic;

import com.bancopichincha.b2b2c.domain.enums.Gender;
import com.bancopichincha.b2b2c.service.TransactionsBusisnessClientService;
import com.bancopichincha.b2b2c.service.dto.TransactionsBusisnessClientDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilterGeneralGender extends AGraphWithFilter {

    private Gender gender;

    public FilterGeneralGender(Gender gender, TransactionsBusisnessClientService transactionsBusisnessClientService) {
        super(transactionsBusisnessClientService);
        this.gender = gender;
    }

    @Override
    List<TransactionsBusisnessClientDto> getDataWithFilers(List<TransactionsBusisnessClientDto> dataSource) {
        return dataSource
                .stream()
                .map(transaction -> {
                    if (this.gender == null) {
                        return dataSource;
                    }
                    return dataSource
                            .stream()
                            .filter(data -> data.getClient().getGender().equals(this.gender)).collect(Collectors.toList());
                }).findAny().orElse(new ArrayList<>());
    }
}