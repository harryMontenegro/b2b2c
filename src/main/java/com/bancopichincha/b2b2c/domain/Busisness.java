package com.bancopichincha.b2b2c.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany
    @JoinColumn(name = "busisness_id", referencedColumnName = "busisness_id")
    private List<BusisnessClient> busisnessClient;

}