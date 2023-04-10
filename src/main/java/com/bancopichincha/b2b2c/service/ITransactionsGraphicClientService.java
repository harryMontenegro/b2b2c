package com.bancopichincha.b2b2c.service;

import java.util.List;
import java.util.Map;

public interface ITransactionsGraphicClientService {
    List<Map.Entry<Integer, Map<String, Long>>> graphTransactions(Integer busisness_id);
}
