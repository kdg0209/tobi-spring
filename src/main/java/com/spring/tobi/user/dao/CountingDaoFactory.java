package com.spring.tobi.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CountingDaoFactory {

    @Bean
    public UserDao userDao() {
        return new UserDao(connectionMarker());
    }

    @Bean
    public ConnectionMarker connectionMarker() {
        return new CountingConnectionMarker(realConnectionMarker());
    }

    @Bean
    ConnectionMarker realConnectionMarker() {
        return new DConnectionMarker();
    }
}
