package com.security.app.configuration;

import com.security.app.services.UsersDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsersDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
                http.authorizeRequests().antMatchers("/","/home","/index","/register").permitAll()

                .antMatchers("/users/**").hasAnyAuthority("USERS","ADMIN")
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                //.anyRequest().fullyAuthenticated()

                .and()
                .formLogin()
                .defaultSuccessUrl("/dashboard").failureUrl("/loginPage?error")
                .and()
                .exceptionHandling().accessDeniedPage("/access_denied");

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    /*@Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }*/


    //particularly used for auto login as per my knowledge
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
