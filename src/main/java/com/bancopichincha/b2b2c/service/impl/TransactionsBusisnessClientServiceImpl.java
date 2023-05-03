package com.bancopichincha.b2b2c.service.impl;

import com.bancopichincha.b2b2c.domain.TransactionsBusisnessClient;
import com.bancopichincha.b2b2c.domain.enums.Gender;
import com.bancopichincha.b2b2c.repository.TransactionsBusisnessClientRepository;
import com.bancopichincha.b2b2c.service.TransactionsBusisnessClientService;
import com.bancopichincha.b2b2c.service.dto.PaginableDTO;
import com.bancopichincha.b2b2c.service.dto.ResultTransaction;
import com.bancopichincha.b2b2c.service.dto.TransactionsBusisnessClientDto;
import com.bancopichincha.b2b2c.service.mapper.MapperObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TransactionsBusisnessClientServiceImpl implements TransactionsBusisnessClientService {

    private final TransactionsBusisnessClientRepository repository;
    private final MapperObject mapper;

    @Autowired
    public TransactionsBusisnessClientServiceImpl(TransactionsBusisnessClientRepository repository, MapperObject mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public TransactionsBusisnessClientDto create(TransactionsBusisnessClientDto transactionsBusisnessClientDto) {
        return mapper.map().convertValue(repository.save(mapper.map().convertValue(transactionsBusisnessClientDto, TransactionsBusisnessClient.class)), TransactionsBusisnessClientDto.class);
    }

    @Override
    public List<TransactionsBusisnessClientDto> list(PaginableDTO pageable) {
        return Optional.of(repository.findAll(PageRequest.of(pageable.getPagina(), pageable.getCantidad())))
                .get()
                .get()
                .map(c -> mapper.map().convertValue(c, TransactionsBusisnessClientDto.class)).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public TransactionsBusisnessClientDto update(TransactionsBusisnessClientDto transactionsBusisnessClientDto) {
        return create(transactionsBusisnessClientDto);
    }

    @Override
    public Optional<TransactionsBusisnessClientDto> findOneById(Integer id) {
        return Optional.of(Optional.of(mapper.map().convertValue(repository.findById(id).orElse(new TransactionsBusisnessClient()), TransactionsBusisnessClientDto.class))).orElse(null);
    }

    @Override
    public List<ResultTransaction> graphTransactions(Integer busisness_id, Gender gender) {

        List<ResultTransaction> response = new ArrayList<>();

        List<TransactionsBusisnessClientDto> dataSource = findByBusisness(busisness_id);

        List<String> anios = dataSource
                .stream()
                .map(trans -> String.valueOf(trans.getTransactionDate().getYear()))
                .distinct()
                .collect(Collectors.toList());

        List<String> allMonth = Stream.of(Month.values()).map(Month::toString).collect(Collectors.toList());

        anios.forEach(anio -> {

            ResultTransaction itemResponse = new ResultTransaction();
            itemResponse.setYear(anio);

            List<TransactionsBusisnessClientDto> monthList;
            if (gender == null) {
                monthList = dataSource
                        .stream()
                        .filter(trans -> String.valueOf(trans.getTransactionDate().getYear()).equals(anio))
                        .collect(Collectors.toList());
            } else {
                monthList = dataSource
                        .stream()
                        .filter(trans -> trans.getClient().stream().anyMatch(client -> client.getGender().equals(gender)))
                        .filter(trans -> String.valueOf(trans.getTransactionDate().getYear()).equals(anio))
                        .collect(Collectors.toList());
            }

            List<String> month = monthList
                    .stream()
                    .map(trans -> trans.getTransactionDate().getMonth().toString())
                    .distinct()
                    .collect(Collectors.toList());

            if (allMonth.stream().anyMatch(single -> month.stream().noneMatch(monthItem -> monthItem.equals(single)))) {
                month.addAll(allMonth.stream().filter(al -> month.stream().noneMatch(m -> m.equals(al))).collect(Collectors.toList()));
            }

            List<TransactionsBusisnessClientDto> finalMonthList = monthList;
            month.forEach(mo -> {

                if (itemResponse.getData().stream().noneMatch(data -> data.containsKey(mo))) {
                    Long count = finalMonthList.stream().filter(ml -> ml.getTransactionDate().getMonth().toString().equals(mo)).count();
                    Map<String, Long> mapData = new HashMap<>();
                    mapData.put(mo, count);
                    itemResponse.getData().add(mapData);
                }
            });
            response.add(itemResponse);
        });

        return response;
    }

    @Override
    public List<TransactionsBusisnessClientDto> findByBusisness(Integer busisness_id) {
        return repository.findByBusisness(busisness_id)
                .stream()
                .map(transaction -> mapper.map().convertValue(transaction, TransactionsBusisnessClientDto.class))
                .collect(Collectors.toList());
    }
}
