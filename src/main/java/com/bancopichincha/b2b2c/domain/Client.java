package com.bancopichincha.b2b2c.domain;

import com.bancopichincha.b2b2c.domain.enums.CivilStatus;
import com.bancopichincha.b2b2c.domain.enums.DocumentType;
import com.bancopichincha.b2b2c.domain.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "client", schema = "b2b2c")
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer client_id;
    private String name;
    private String lastName;
    @Column(name = "document_type")
    private DocumentType documentType;
    private String dni;
    @Column(name = "civil_status")
    private CivilStatus civilStatus;
    private Gender gender;
    private LocalDate birthdate;

    @OneToOne
    @JoinColumn(name = "client_id", referencedColumnName = "client_id")
    private Address address;
}
