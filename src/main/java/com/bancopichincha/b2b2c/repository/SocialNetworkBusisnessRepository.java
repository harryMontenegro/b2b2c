package com.bancopichincha.b2b2c.repository;

import com.bancopichincha.b2b2c.domain.SocialNetworkBusisness;
import com.bancopichincha.b2b2c.domain.enums.SocialNetwork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialNetworkBusisnessRepository extends JpaRepository<SocialNetworkBusisness, Integer> {
    @Query("SELECT t FROM SocialNetworkBusisness t WHERE t.socialNetwork = ?1 AND t.name = ?2 AND t.busisness_id = ?3")
    SocialNetworkBusisness findByUnique(SocialNetwork socialNetwork, String name, Integer busisness_id);
}
