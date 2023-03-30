package com.bancopichincha.b2b2c.domain;

import com.bancopichincha.b2b2c.domain.enums.SocialNetwork;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "socialnetwork_busisness", schema = "b2b2c")
@Getter
@Setter
public class SocialNetworkBusisness {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer socialnetwork_busisness_id;
    private Integer busisness_id;
    private SocialNetwork socialNetwork;
    private String name;

}
