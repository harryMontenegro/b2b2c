package com.bancopichincha.b2b2c.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "transactionsbusisnessclient", schema = "b2b2c")
@Getter
@Setter
public class TransactionsBusisnessClient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionsBusisnessClient_id;
    @Column(insertable = false,  updatable = false)
    private Integer client_id;
    private Integer busisness_id;
    private LocalDate transactionDate;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "client_id")
    private Client client;
}
