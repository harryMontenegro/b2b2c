package com.bancopichincha.b2b2c.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "modulequalification", schema = "b2b2c")
@Getter
@Setter
public class ModuleQualification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer modulequalification_id;
    private String graphic;
    private Integer busisness_id;
    private Integer qualification;
    private LocalDate qualificationDate;
}
