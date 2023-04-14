package com.bancopichincha.b2b2c.service;

import com.bancopichincha.b2b2c.domain.enums.Gender;
import com.bancopichincha.b2b2c.service.dto.ResultTransaction;

import java.util.List;

public interface ITransactionsGraphicClientService {
    List<ResultTransaction>  graphTransactions(Integer busisness_id, Gender gender);
}
