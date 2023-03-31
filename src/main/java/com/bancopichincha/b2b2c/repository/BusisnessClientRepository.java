package com.bancopichincha.b2b2c.repository;

import com.bancopichincha.b2b2c.domain.BusisnessClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusisnessClientRepository extends JpaRepository<BusisnessClient, Integer> {
    @Query("SELECT t FROM BusisnessClient t WHERE t.busisness_id = ?1 AND t.client_id = ?2")
    BusisnessClient findByUnique(Integer busisness_id, Integer client_id);

    @Query("SELECT t FROM BusisnessClient t WHERE t.busisness_id = ?1")
    List<BusisnessClient> findByBusisness(Integer busisness_id);
}
