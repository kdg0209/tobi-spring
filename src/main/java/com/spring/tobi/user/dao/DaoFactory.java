package com.spring.tobi.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

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
        UserDao userDao = new UserDao();
        return userDao;
    }

    @Bean
    public DataSource dataSource() {

    }
}
