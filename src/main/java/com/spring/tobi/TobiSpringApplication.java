package com.spring.tobi;

import com.spring.tobi.user.domain.Level;
import com.spring.tobi.user.domain.User;
import com.spring.tobi.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@RequiredArgsConstructor
public class TobiSpringApplication {
    private final UserService userService;
    public static void main(String[] args) {
        SpringApplication.run(TobiSpringApplication.class, args);
    }
    @PostConstruct
    public void jdbcTest() {

        User user1 = new User();
        user1.setId("kdg1");
        user1.setName("kdg");
        user1.setPassword("12345");
        user1.setLevel(Level.BASIC);
        user1.setLogin(10);
        user1.setRecommend(10);
        user1.setEmail("s_cova_0418@naver.com");
        userService.add(user1);

        User user2 = new User();
        user2.setId("kdg2");
        user2.setName("kdg");
        user2.setPassword("12345");
        user2.setLevel(Level.BASIC);
        user2.setLogin(20);
        user2.setRecommend(20);
        user2.setEmail("s_cova_0418@naver.com");
        userService.add(user2);

        User user3 = new User();
        user3.setId("kdg3");
        user3.setName("kdg");
        user3.setPassword("12345");
        user3.setLevel(Level.BASIC);
        user3.setLogin(30);
        user3.setRecommend(30);
        user3.setEmail("s_cova_0418@naver.com");
        userService.add(user3);

        User user4 = new User();
        user4.setId("kdg4");
        user4.setName("kdg");
        user4.setPassword("12345");
        user4.setLevel(Level.BASIC);
        user4.setLogin(50);
        user4.setRecommend(40);
        user4.setEmail("s_cova_0418@naver.com");
        userService.add(user4); // SILVER로 승격

        User user5 = new User();
        user5.setId("kdg5");
        user5.setName("kdg");
        user5.setPassword("12345");
        user5.setLevel(Level.SILVER);
        user5.setLogin(50);
        user5.setRecommend(50);
        user5.setEmail("s_cova_0418@naver.com");
        userService.add(user5); // GOLD로 승격

        userService.upgradeLevels();
    }
}
