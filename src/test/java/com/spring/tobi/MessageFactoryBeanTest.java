package com.spring.tobi;

import com.spring.tobi.factoryBean.Message;
import com.spring.tobi.factoryBean.MessageFactoryBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class MessageFactoryBeanTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void getMessageFromFactoryBean() {
        Object message = applicationContext.getBean("messages");
        if (message instanceof Message) {
            System.out.println(message);
            System.out.println(((Message) message).getText());
            System.out.println("Same");
        }
    }

    @Test
    public void getFactoryBean() {
        Object factory = applicationContext.getBean("&messages");
        if (factory instanceof MessageFactoryBean) {
            System.out.println("Yes");
        }
    }
}
