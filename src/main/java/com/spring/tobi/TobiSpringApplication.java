package com.spring.tobi;

import com.spring.tobi.user.dao.DConnectionMarker;
import com.spring.tobi.user.dao.UserDao;
import com.spring.tobi.user.domain.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.sql.SQLException;

@SpringBootApplication
public class TobiSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(TobiSpringApplication.class, args);

    }

    @PostConstruct
    public void jdbcTest() throws ClassNotFoundException, SQLException {
        UserDao userDao = new UserDao(new DConnectionMarker());

        User user = new User();
        user.setId("kdg");
        user.setName("김동균");
        user.setPassword("12345");
        userDao.add(user);
        System.out.println("유저 등록 성공");

        User findUser = userDao.get(user.getId());
        System.out.println(findUser.toString());
        System.out.println("유저 조회 성공");
    }

}
