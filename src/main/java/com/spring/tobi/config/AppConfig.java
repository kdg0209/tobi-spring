package com.spring.tobi.config;

import com.spring.tobi.aop.NameMatchClassMethodPointcut;
import com.spring.tobi.aop.TransactionAdvice;
import com.spring.tobi.factoryBean.MessageFactoryBean;
import com.spring.tobi.factoryBean.TxProxyFactoryBean;
import com.spring.tobi.user.dao.UserDao;
import com.spring.tobi.user.dao.UserDaoJdbc;
import com.spring.tobi.user.service.UserService;
import com.spring.tobi.user.service.UserServiceImpl;
import com.spring.tobi.user.service.UserServiceTx;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    @Bean
    public MessageFactoryBean messages() {
        MessageFactoryBean messageFactoryBean = new MessageFactoryBean();
        messageFactoryBean.setText("Factory Bean");
        return messageFactoryBean;
    }
    @Bean
    public UserService userService() {
        return userServiceImpl(userDao(), mailSender());
    }
    @Bean
    public TransactionAdvice transactionAdvice() {
        TransactionAdvice transactionAdvice = new TransactionAdvice();
        transactionAdvice.setTransactionManager(platformTransactionManager());
        return transactionAdvice;
    }

//    @Bean
//    public NameMatchClassMethodPointcut transactionPointcut() {
//        NameMatchClassMethodPointcut pointcut = new NameMatchClassMethodPointcut();
//        pointcut.setMappedClassName("*ServiceImpl");
//        pointcut.setMappedName("upgrade*");
//        return pointcut;
//    }

    @Bean
    public DefaultPointcutAdvisor transactionAdvisor() {
        DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor();
        defaultPointcutAdvisor.setPointcut(transactionPointcut());
        defaultPointcutAdvisor.setAdvice(transactionAdvice());
        return defaultPointcutAdvisor;
    }
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        return new DefaultAdvisorAutoProxyCreator();
    }
    @Bean
    public AspectJExpressionPointcut transactionPointcut() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("bean(*Service)");
        return pointcut;
    }
}
