package com.bancopichincha.b2b2c.repository;

import com.bancopichincha.b2b2c.domain.ModuleQualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleQualificationRepository extends JpaRepository<ModuleQualification, Integer> {
    @Query("SELECT t FROM ModuleQualification t WHERE t.graphic = ?1 AND t.busisness_id = ?2")
    ModuleQualification findByUnique(String graphic, Integer busisness_id);
}
