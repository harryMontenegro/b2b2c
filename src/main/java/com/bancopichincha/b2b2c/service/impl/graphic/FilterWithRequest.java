package com.bancopichincha.b2b2c.service.impl.graphic;

import com.bancopichincha.b2b2c.service.TransactionsBusisnessClientService;
import com.bancopichincha.b2b2c.service.dto.FilterRequest;
import com.bancopichincha.b2b2c.service.dto.TransactionsBusisnessClientDto;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class FilterWithRequest extends AGraphWithFilter {

    private FilterRequest filterRequest;

    public FilterWithRequest(FilterRequest filterRequest, TransactionsBusisnessClientService transactionsBusisnessClientService) {
        super(transactionsBusisnessClientService);
        this.filterRequest = filterRequest;
    }

    @Override
    List<TransactionsBusisnessClientDto> getDataWithFilers(List<TransactionsBusisnessClientDto> dataSource) {

        AtomicReference<List<TransactionsBusisnessClientDto>> reference = new AtomicReference<>(dataSource);

        filterRequest.getOptionalDateFilter().ifPresent(date -> reference.set(
                reference.get()
                        .stream()
                        .filter(t -> t.getTransactionDate().isAfter(date.getDateFrom()) &&
                                    t.getTransactionDate().isBefore(date.getDateTo())).collect(Collectors.toList())));


        filterRequest.getOptionalGender().ifPresent(genderList -> {
            reference.set(reference.get()
                    .stream()
                    .filter(transactions -> genderList
                            .stream()
                            .anyMatch(gender -> transactions.getClient().getGender().equals(gender)))
                    .collect(Collectors.toList()));
        });

        filterRequest.getOptionalCivilStatus().ifPresent(civilStatusList -> {
            reference.set(reference.get()
                    .stream()
                    .filter(transactions -> civilStatusList
                            .stream()
                            .anyMatch(civilStatus -> transactions.getClient().getCivilStatus().equals(civilStatus)))
                    .collect(Collectors.toList()));
        });

        filterRequest.getOptionalCity().ifPresent(cityList -> {
            reference.set(reference.get()
                    .stream()
                    .filter(transactions -> cityList
                            .stream()
                            .anyMatch(city -> transactions.getClient().getAddress().getCity().equals(city)))
                    .collect(Collectors.toList()));
        });

        return reference.get();
    }
}