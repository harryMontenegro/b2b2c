package com.bancopichincha.b2b2c.repository;

import com.bancopichincha.b2b2c.domain.Client;
import com.bancopichincha.b2b2c.domain.enums.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    @Query("SELECT t FROM Client t WHERE t.documentType = ?1 AND t.dni = ?2")
    Client findByUnique(DocumentType ruc, String dni);
}
