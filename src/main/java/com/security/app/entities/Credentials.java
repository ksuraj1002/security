package com.security.app.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Credentials {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String userName;
    public String password;
    public boolean isAccountNonExpired;

    @Enumerated(EnumType.STRING)
    public Roles roles;

    @OneToOne(mappedBy = "credentials")
    public Person person;
}
