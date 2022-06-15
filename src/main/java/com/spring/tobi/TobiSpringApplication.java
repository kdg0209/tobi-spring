package com.spring.tobi;

import com.spring.tobi.user.dao.DConnectionMarker;
import com.spring.tobi.user.dao.DaoFactory;
import com.spring.tobi.user.dao.UserDao;
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

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        SpringApplication.run(TobiSpringApplication.class, args);
    }

    @PostConstruct
    public void jdbcTest() throws ClassNotFoundException, SQLException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao1 = applicationContext.getBean("userDao", UserDao.class);
        UserDao dao2 = applicationContext.getBean("userDao", UserDao.class);
        UserDao dao3 = applicationContext.getBean("accountDao", UserDao.class);
        UserDao dao4 = applicationContext.getBean("accountDao", UserDao.class);

        System.out.println("applicationContext call");
        System.out.println(dao1);
        System.out.println(dao2);
        System.out.println(dao3);
        System.out.println(dao4);

        System.out.println("daoFactory call");
        System.out.println(daoFactory.userDao());
        System.out.println(daoFactory.userDao());

//        User user = new User();
//        user.setId("kdg");
//        user.setName("김동균");
//        user.setPassword("12345");
//        userDao.add(user);
//        System.out.println("유저 등록 성공");
//
//        User findUser = userDao.get(user.getId());
//        System.out.println(findUser.toString());
//        System.out.println("유저 조회 성공");
    }

}
