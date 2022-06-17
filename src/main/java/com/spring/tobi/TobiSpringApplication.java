package com.spring.tobi;

import com.spring.tobi.user.dao.*;
import com.spring.tobi.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;
import java.sql.SQLException;

@SpringBootApplication
@RequiredArgsConstructor
public class TobiSpringApplication {

    private final DaoFactory daoFactory;
    private final CountingDaoFactory countingDaoFactory;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        SpringApplication.run(TobiSpringApplication.class, args);
    }

    @PostConstruct
    public void jdbcTest() throws ClassNotFoundException, SQLException {

        User findUser = daoFactory.userDao().get("kdg");
        System.out.println(findUser.getName());
    }
}
