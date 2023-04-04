package com.bancopichincha.b2b2c.service.impl;

import com.bancopichincha.b2b2c.domain.TransactionsBusisnessClient;
import com.bancopichincha.b2b2c.repository.TransactionsBusisnessClientRepository;
import com.bancopichincha.b2b2c.service.TransactionsBusisnessClientService;
import com.bancopichincha.b2b2c.service.dto.PaginableDTO;
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
    public Map<Integer, Map<String, Long>> graphTransactions(Integer busisness_id) {

        Map<Integer, Map<String, Long>> response = new HashMap<>();

        List<TransactionsBusisnessClientDto> dataSource = findByBusisness(busisness_id);

        dataSource.forEach(transaction -> {
                    Long count = dataSource
                            .stream()
                            .filter(t -> t.getTransactionDate().getMonth().equals(transaction.getTransactionDate().getMonth())
                                    && t.getTransactionDate().getYear() == transaction.getTransactionDate().getYear()).count();

                    if (!response.containsKey(transaction.getTransactionDate().getYear())) {
                        Map<String, Long> month = new HashMap<>();
                        month.put(transaction.getTransactionDate().getMonth().toString(), count);
                        response.put(transaction.getTransactionDate().getYear(), month);

                    } else {

                        if (!response.get(transaction.getTransactionDate().getYear()).containsKey(transaction.getTransactionDate().getMonth().toString())) {
                            Map<String, Long> existentYear = new HashMap<>(response.get(transaction.getTransactionDate().getYear()));
                            existentYear.put(transaction.getTransactionDate().getMonth().toString(), count);
                            response.put(transaction.getTransactionDate().getYear(), existentYear);
                        }
                    }
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
