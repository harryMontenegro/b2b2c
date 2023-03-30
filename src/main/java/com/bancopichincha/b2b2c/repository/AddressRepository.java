package com.bancopichincha.b2b2c.repository;

import com.bancopichincha.b2b2c.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    @Query("SELECT t FROM Address t WHERE t.client_id = ?1")
    Address findByUnique(Integer id);
}
