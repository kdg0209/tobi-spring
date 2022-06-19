package com.spring.tobi;

import com.spring.tobi.calculator.Calculator;
import com.spring.tobi.user.dao.*;
import com.spring.tobi.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
        User user = new User();
        user.setId("kdg 2");
        user.setName("kdg is 2");
        user.setPassword("12345");
        daoFactory.userDao().add(user);
        System.out.println(daoFactory.userDao().getCount());
//        System.out.println(daoFactory.userDao().get("kdg"));
        List<User> users = daoFactory.userDao().getAll();
        users.forEach(i -> System.out.println(i.toString()));
//        daoFactory.userDao().deleteAll();

    }
}
