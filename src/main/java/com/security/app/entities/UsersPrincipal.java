package com.security.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class UsersPrincipal implements UserDetails, Serializable {

    public String name;
    public Float age;
    public String qualification;
    public String userName;

    @JsonIgnore
    private boolean isAccountNonExpired;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private Collection<? extends GrantedAuthority> authorities;

    public UsersPrincipal(String name, Float age, String qualification, String userName, Collection<? extends GrantedAuthority> authorities,String password,boolean isAccountNonExpired) {
        this.name = name;
        this.age = age;
        this.qualification = qualification;
        this.userName = userName;
        this.authorities = authorities;
        this.password=password;
        this.isAccountNonExpired=isAccountNonExpired;
    }

    public static UsersPrincipal createPrincipal(Person person) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(person.getCredentials().getRoles().toString()));
        return new UsersPrincipal(person.getName(),person.getAge(),person.getQualification(),person.getCredentials().getUserName(),authorities,person.getCredentials().getPassword(),person.getCredentials().isAccountNonExpired);
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
