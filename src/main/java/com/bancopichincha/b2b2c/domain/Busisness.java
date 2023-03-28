package com.bancopichincha.b2b2c.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "busisness", schema = "b2b2c")
@Getter
@Setter
public class Busisness {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer busisness_id;
    private String name;
    private String ruc;
}