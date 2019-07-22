package com.company.config.secutity;

import com.company.service.ClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan(value = "com.company")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final ClientDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(ClientDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
        ///////////////////////////////////////////
//        auth.inMemoryAuthentication()
//                .withUser("admin").password(encoder().encode("pass")).roles("ADMIN")
//                .and()
//                .withUser("user").password(encoder().encode("pass")).roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.GET, "/faculties/").access("hasAnyRole('USER', 'ADMIN')")
                .antMatchers(HttpMethod.POST, "/faculties/").access("hasRole('ADMIN')")
                .antMatchers(HttpMethod.PUT, "/faculties/*").access("hasRole('ADMIN')")
                .antMatchers(HttpMethod.DELETE, "/faculties/*").access("hasRole('ADMIN')")
                .antMatchers("/students/**").access("hasRole('ADMIN')")
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll();
    }
}
