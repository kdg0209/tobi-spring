package com.spring.tobi;

import com.spring.tobi.aop.*;
import com.spring.tobi.config.AppConfig;
import com.spring.tobi.factoryBean.Message;
import com.spring.tobi.user.domain.Level;
import com.spring.tobi.user.domain.User;
import com.spring.tobi.user.service.UserServiceImpl;
import com.spring.tobi.user.service.UserServiceTx;
import lombok.RequiredArgsConstructor;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.PostConstruct;
import java.lang.reflect.Proxy;

@SpringBootApplication
@RequiredArgsConstructor
public class TobiSpringApplication {
//    private final UserServiceTx userService;
//    private final UserServiceImpl userServiceImpl;
//    private final UserServiceTx userServiceTx;

    @Autowired
    private ApplicationContext applicationContext;
    public static void main(String[] args) throws Exception {
        SpringApplication.run(TobiSpringApplication.class, args);

        TobiSpringApplication example = new TobiSpringApplication();
        example.pointcutAdvisor();
    }
    @PostConstruct
    public void jdbcTest() throws Exception {

//        User user1 = new User();
//        user1.setId("kdg1");
//        user1.setName("kdg");
//        user1.setPassword("12345");
//        user1.setLevel(Level.BASIC);
//        user1.setLogin(10);
//        user1.setRecommend(10);
//        user1.setEmail("s_cova_0418@naver.com");
//        userService.add(user1);
//
//        User user2 = new User();
//        user2.setId("kdg2");
//        user2.setName("kdg");
//        user2.setPassword("12345");
//        user2.setLevel(Level.BASIC);
//        user2.setLogin(20);
//        user2.setRecommend(20);
//        user2.setEmail("s_cova_0418@naver.com");
//        userService.add(user2);
//
//        User user3 = new User();
//        user3.setId("kdg3");
//        user3.setName("kdg");
//        user3.setPassword("12345");
//        user3.setLevel(Level.BASIC);
//        user3.setLogin(30);
//        user3.setRecommend(30);
//        user3.setEmail("s_cova_0418@naver.com");
//        userService.add(user3);
//
//        User user4 = new User();
//        user4.setId("kdg4");
//        user4.setName("kdg");
//        user4.setPassword("12345");
//        user4.setLevel(Level.BASIC);
//        user4.setLogin(50);
//        user4.setRecommend(40);
//        user4.setEmail("s_cova_0418@naver.com");
//        userService.add(user4); // SILVER로 승격
//
//        User user5 = new User();
//        user5.setId("kdg5");
//        user5.setName("kdg");
//        user5.setPassword("12345");
//        user5.setLevel(Level.SILVER);
//        user5.setLogin(50);
//        user5.setRecommend(50);
//        user5.setEmail("s_cova_0418@naver.com");
//        userService.add(user5); // GOLD로 승격
//
//        userService.upgradeLevels();
    }

    private static void simpleProxy() {
        Hello hello = new HelloUppercaseProxy(new HelloTarget());
        System.out.println(hello);
        System.out.println(hello.sayHello("KDG"));
        System.out.println(hello.sayThankYou("KDG"));
    }

    private void proxyCreate() {
//        Hello helloProxy = (Hello) Proxy.newProxyInstance(
//                ClassLoader.getSystemClassLoader(),
//                new Class[] {Hello.class},
//                new UppercaseHandler(new HelloTarget()));
//        System.out.println(helloProxy);
//        System.out.println(helloProxy.sayHello("KDG"));

        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(new HelloTarget());
        proxyFactoryBean.addAdvice(new UppercaseAdvice());

        Hello proxiedHello = (Hello) proxyFactoryBean.getObject();
        System.out.println(proxiedHello.sayHello("Toby").equals("HELLO TOBY"));
        System.out.println(proxiedHello.sayHi("Toby").equals("HI TOBY"));
        System.out.println(proxiedHello.sayThankYou("Toby").equals("THANK YOU TOBY"));
    }

    private void pointcutAdvisor() {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(new HelloTarget());

        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedName("sayH*");
        proxyFactoryBean.addAdvisor(new DefaultPointcutAdvisor(pointcut, new UppercaseAdvice()));

        Hello proxiedHello = (Hello) proxyFactoryBean.getObject();
        System.out.println(proxiedHello.sayHello("Toby"));
        System.out.println(proxiedHello.sayHi("Toby"));
        System.out.println(proxiedHello.sayThankYou("Toby"));
    }

    static class UppercaseAdvice implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            String ref = (String) invocation.proceed();
            return ref.toUpperCase();
        }
    }
}
