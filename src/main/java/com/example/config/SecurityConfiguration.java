package com.example.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity   //开启WebSecurity相关功能

public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public PersistentTokenRepository tokenRepository(DataSource dataSource){
        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();
        //在启动时自动在数据库中创建存储记住我信息的表，仅第一次需要，后续不需要
//        repository.setCreateTableOnStartup(true);
        repository.setDataSource(dataSource);
        return repository;


    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, PersistentTokenRepository repository) throws Exception {
        return http.authorizeHttpRequests(auth->{
                    auth.requestMatchers("/static/**").permitAll();
                    auth.anyRequest().authenticated();
                })
                .formLogin(conf->{
                    conf.loginPage("/login");
                    conf.loginProcessingUrl("/doLogin");
                    conf.defaultSuccessUrl("/");
                    conf.permitAll();
                })
                .rememberMe(conf->{
                    conf.tokenRepository(repository);
                    conf.tokenValiditySeconds(3600 * 7 * 24);
                })
                .logout(conf->{
                    conf.logoutUrl("/doLogout");
                    conf.logoutSuccessUrl("/login");
                    conf.permitAll();
                })
                .csrf(AbstractHttpConfigurer::disable)
                .build();

    }






}