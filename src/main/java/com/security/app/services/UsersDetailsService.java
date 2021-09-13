package com.security.app.services;

import com.security.app.entities.Person;
import com.security.app.entities.UsersPrincipal;
import com.security.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class UsersDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person=userRepository.findByCredentials_UserName(username);
        UsersPrincipal usersPrincipal=null;
        if(person.isPresent()){
            usersPrincipal = UsersPrincipal.createPrincipal(person.get());
        }

        //for simple login conventional way
      /*  if(person==null) {
            throw new UsernameNotFoundException("Username not found for "+person);
        }
        GrantedAuthority authority=new SimpleGrantedAuthority(person.getCredentials().getRoles().toString());
        UserDetails userDetails=(UserDetails)new User(person.getCredentials().getUserName(),person.getCredentials().getPassword(), Arrays.asList(authority));
        return userDetails; */
      return usersPrincipal;
    }

}
