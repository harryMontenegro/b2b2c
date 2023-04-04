package com.bancopichincha.b2b2c.service;

import java.util.Map;

public interface ITransactionsGraphicClientService {
    Map<Integer, Map<String, Long>> graphTransactions(Integer busisness_id);
}
