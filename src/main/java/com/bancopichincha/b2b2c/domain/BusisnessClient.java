package com.bancopichincha.b2b2c.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "busisness_client", schema = "b2b2c")
@Getter
@Setter
public class BusisnessClient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer busisness_client_id ;
    private Integer busisness_id;
    private Integer client_id;
}
