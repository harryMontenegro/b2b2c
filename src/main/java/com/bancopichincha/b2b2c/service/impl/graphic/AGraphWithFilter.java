package com.bancopichincha.b2b2c.service.impl.graphic;

import com.bancopichincha.b2b2c.service.TransactionsBusisnessClientService;
import com.bancopichincha.b2b2c.service.dto.ResultTransaction;
import com.bancopichincha.b2b2c.service.dto.TransactionsBusisnessClientDto;

import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public abstract class AGraphWithFilter {
    private TransactionsBusisnessClientService transactionsBusisnessClientService;

    public AGraphWithFilter(TransactionsBusisnessClientService transactionsBusisnessClientService) {
        this.transactionsBusisnessClientService = transactionsBusisnessClientService;
    }

    public final List<ResultTransaction> getReturnDataGraphic(Integer busisness_id) {
        List<ResultTransaction> response = new ArrayList<>();
        List<TransactionsBusisnessClientDto> dataSource = transactionsBusisnessClientService.findByBusisness(busisness_id);

        List<TransactionsBusisnessClientDto> dataFilter = getDataWithFilers(dataSource);

        List<String> anios = dataSource
                .stream()
                .map(trans -> String.valueOf(trans.getTransactionDate().getYear()))
                .distinct()
                .collect(Collectors.toList());

        List<String> allDefaultMonth = Stream.of(Month.values()).map(Month::toString).collect(Collectors.toList());

        anios.forEach(anio -> {

            ResultTransaction itemResponse = new ResultTransaction();
            itemResponse.setYear(anio);

            List<String> dataMonthSaved = dataFilter
                    .stream()
                    .map(trans -> trans.getTransactionDate().getMonth().toString())
                    .distinct()
                    .collect(Collectors.toList());

            if (allDefaultMonth.stream().anyMatch(single -> dataMonthSaved.stream().noneMatch(monthItem -> monthItem.equals(single)))) {
                dataMonthSaved.addAll(allDefaultMonth.stream().filter(al -> dataMonthSaved.stream().noneMatch(m -> m.equals(al))).collect(Collectors.toList()));
            }

            dataMonthSaved.forEach(mo -> {
                if (itemResponse.getData().stream().noneMatch(data -> data.containsKey(mo))) {
                    Long count = dataFilter
                            .stream()
                            .filter(ml -> ml.getTransactionDate().getMonth().toString().equals(mo) &&
                                    String.valueOf(ml.getTransactionDate().getYear()).equals(anio)).count();
                    Map<String, Long> mapData = new HashMap<>();
                    mapData.put(mo, count);
                    itemResponse.getData().add(mapData);
                }
            });
            response.add(itemResponse);
        });
        return response;
    }

    abstract List<TransactionsBusisnessClientDto> getDataWithFilers(List<TransactionsBusisnessClientDto> dataSource);

}
