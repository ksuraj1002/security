package com.security.app.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String name;
    public Float age;
    public String qualification;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="credential_id")
    public Credentials credentials;
}
