package com.spring.tobi.config;

import com.spring.tobi.user.dao.UserDao;
import com.spring.tobi.user.dao.UserDaoJdbc;
import com.spring.tobi.user.service.UserService;
import com.spring.tobi.user.service.UserServiceImpl;
import com.spring.tobi.user.service.UserServiceTx;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.mail.MailSender;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Bean
    public MailSender mailSender() {
        return new DummyMailSender();
    }

    @Bean
    public UserDao userDao() {
        UserDaoJdbc userDaoJdbc = new UserDaoJdbc();
        userDaoJdbc.setDataSource(dataSource());
        return userDaoJdbc;
    }

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
        dataSource.setUrl("jdbc:mysql://localhost:3306/tobi-spring?serverTimezone=Asia/Seoul");
        dataSource.setUsername("root");
        dataSource.setPassword("12345");
        return dataSource;
    }

    @Bean
    @Primary
    public UserServiceTx userServiceTx(UserService userService, PlatformTransactionManager transactionManager) {
        UserServiceTx userServiceTx = new UserServiceTx();
        userServiceTx.setUserService(userService);
        userServiceTx.setTransactionManager(transactionManager);
        return userServiceTx;
    }

    @Bean
    PlatformTransactionManager platformTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public UserServiceImpl userServiceImpl(UserDao userDao, MailSender mailSender) {
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        userServiceImpl.setUserDao(userDao);
        userServiceImpl.setMailSender(mailSender);
        return userServiceImpl;
    }
}
