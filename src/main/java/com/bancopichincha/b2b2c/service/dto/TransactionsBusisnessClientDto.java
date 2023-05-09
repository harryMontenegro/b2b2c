package com.bancopichincha.b2b2c.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class TransactionsBusisnessClientDto {
    private Integer transactionsBusisnessClient_id;
    private Integer client_id;
    private Integer busisness_id;
    private LocalDate transactionDate;
    private ClientDto client;

    @Override
    public String toString() {
        return  transactionDate.toString();
    }
}
