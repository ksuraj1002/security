package com.security.app.services;

import com.security.app.entities.Credentials;
import com.security.app.entities.Person;
import com.security.app.entities.Roles;
import com.security.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CommonServices {
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UsersDetailsService usersDetailsService;
    @Autowired
    AuthenticationManager authenticationManager;

    public void saveUser(Person person, Credentials credentials) {
        credentials.setPassword(passwordEncoder.encode(credentials.getPassword()));
        credentials.setRoles(Roles.USERS);
        credentials.setAccountNonExpired(true);
        person.setCredentials(credentials);
        userRepository.save(person);
    }

    public void autoLogin(String username, String password) {
        UserDetails userDetails=usersDetailsService.loadUserByUsername(username);

        //Manually authentication
        UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(userDetails, password,userDetails.getAuthorities());
        authenticationManager.authenticate(token);
        if (token.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(token);
        }

    }
}
