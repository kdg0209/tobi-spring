package com.spring.tobi.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {

//    private static UserDao instance;
//
//    public DaoFactory() {}
//
//    public static UserDao getInstance() {
//        if (instance == null) {
//            instance = new UserDao(new DConnectionMarker());
//            return instance;
//        }
//        return instance;
//    }

    @Bean
    public UserDao userDao() {
        UserDao userDao = new UserDao(connectionMarker());
        return userDao;
    }

    @Bean
    public UserDao accountDao() {
        UserDao userDao = new UserDao(connectionMarker());
        return userDao;
    }

    @Bean
    public UserDao messageDao() {
        UserDao userDao = new UserDao(connectionMarker());
        return userDao;
    }

    @Bean
    public ConnectionMarker connectionMarker() {
        return new DConnectionMarker();
    }
}
