package com.security.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UsersPrincipal implements UserDetails, Serializable {

    public String name;
    public Float age;
    public String qualification;
    public String userName;
    public String defaultUrl;

    @JsonIgnore
    private boolean isAccountNonExpired;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private Collection<? extends GrantedAuthority> authorities;

    public UsersPrincipal(String name, Float age, String qualification, String userName, Collection<? extends GrantedAuthority> authorities,String password,boolean isAccountNonExpired,String defaultUrl) {
        this.name = name;
        this.age = age;
        this.qualification = qualification;
        this.userName = userName;
        this.authorities = authorities;
        this.password=password;
        this.isAccountNonExpired=isAccountNonExpired;
        this.defaultUrl=defaultUrl;
    }

    public static UsersPrincipal createPrincipal(Person person) {
     //   roles=[USERS, ADMIN]
        List<GrantedAuthority> authorities = new ArrayList<>();
        //authorities.add(new SimpleGrantedAuthority(person.getCredentials().getRoles().toString()));
      //  System.out.println(person.getCredentials().getRoles().toString().split(","));
        for(Roles roles:person.getCredentials().getRoles()){
            GrantedAuthority authority=new SimpleGrantedAuthority(roles.toString());
            authorities.add(authority);
        }
        //authorities= Arrays.stream(person.getCredentials().getRoles())
               // .stream(Arrays.asList(person.getCredentials().getRoles().toArray())).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return new UsersPrincipal(person.getName(),person.getAge(),person.getQualification(),person.getCredentials().getUserName(),authorities,person.getCredentials().getPassword(),person.getCredentials().isAccountNonExpired,person.getCredentials().getDefaultUrl());
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
