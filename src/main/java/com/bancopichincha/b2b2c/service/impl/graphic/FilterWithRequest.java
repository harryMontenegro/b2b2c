package com.bancopichincha.b2b2c.service.impl.graphic;

import com.bancopichincha.b2b2c.service.TransactionsBusisnessClientService;
import com.bancopichincha.b2b2c.service.dto.FilterRequest;
import com.bancopichincha.b2b2c.service.dto.TransactionsBusisnessClientDto;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class FilterWithRequest extends AGraphWithFilter{

    private FilterRequest filterRequest;

    public FilterWithRequest(FilterRequest filterRequest, TransactionsBusisnessClientService transactionsBusisnessClientService) {
        super(transactionsBusisnessClientService);
        this.filterRequest = filterRequest;
    }

    @Override
    List<TransactionsBusisnessClientDto> getDataWithFilers(List<TransactionsBusisnessClientDto> dataSource) {

        AtomicReference<List<TransactionsBusisnessClientDto>> reference = new AtomicReference<>(dataSource);

        filterRequest.getOptionalDateFilter().ifPresent(date -> reference.getAndSet(dataSource
                .stream()
                .filter(transactions -> transactions.getTransactionDate().isBefore(date.getDateFrom()) &&
                        transactions.getTransactionDate().isAfter(date.getDateTo())).collect(Collectors.toList())));

        return dataSource
                .stream()
                .filter(transactions -> filterRequest.getOptionalGender()
                        .stream().anyMatch(genderList -> genderList
                                .stream()
                                .anyMatch(g -> g.equals(transactions.getClient().getGender()))) &&
                        filterRequest.getOptionalCivilStatus()
                                .stream()
                                .anyMatch(civilStatusList -> civilStatusList
                                        .stream()
                                        .anyMatch(civilStatus -> civilStatus.equals(transactions.getClient().getCivilStatus()))) &&
                        filterRequest.getOptionalCity()
                                .stream()
                                .anyMatch(cityList -> cityList
                                        .stream()
                                        .anyMatch(city -> city.equals(transactions.getClient().getAddress().getCity()))))


                .collect(Collectors.toList());
    }
}