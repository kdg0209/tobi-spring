package com.spring.tobi;

import com.spring.tobi.calculator.Calculator;
import com.spring.tobi.user.dao.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.IOException;
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
    public void jdbcTest() throws ClassNotFoundException, SQLException, IOException {
        daoFactory.userDao().deleteAll();

    }
}
