package com.bancopichincha.b2b2c.repository;

import com.bancopichincha.b2b2c.domain.Busisness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusisnessRepository extends JpaRepository<Busisness, Integer> {
    @Query("SELECT t FROM Busisness t WHERE t.ruc = ?1")
    Busisness findByRuc(String ruc);
}
