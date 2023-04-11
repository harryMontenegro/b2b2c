package com.bancopichincha.b2b2c.service;

import com.bancopichincha.b2b2c.service.dto.ResultTransaction;

import java.util.List;
import java.util.Map;

public interface ITransactionsGraphicClientService {
    List<ResultTransaction>  graphTransactions(Integer busisness_id);
}
