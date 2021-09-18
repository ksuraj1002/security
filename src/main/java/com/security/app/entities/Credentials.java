package com.security.app.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Credentials {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String userName;
    public String password;
    public String defaultUrl;
    public boolean isAccountNonExpired;

    @ElementCollection(targetClass = Roles.class,fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    public Set<Roles> roles;

    @OneToOne(mappedBy = "credentials")
    public Person person;
}
