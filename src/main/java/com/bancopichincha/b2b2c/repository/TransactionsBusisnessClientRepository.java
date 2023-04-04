package com.bancopichincha.b2b2c.repository;

import com.bancopichincha.b2b2c.domain.TransactionsBusisnessClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionsBusisnessClientRepository extends JpaRepository<TransactionsBusisnessClient, Integer> {
    @Query("SELECT t FROM TransactionsBusisnessClient t WHERE t.busisness_id = ?1")
    List<TransactionsBusisnessClient> findByBusisness(Integer id);
}
