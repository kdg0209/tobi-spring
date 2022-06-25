package com.spring.tobi.factoryBean;

import org.springframework.beans.factory.FactoryBean;

public class MessageFactoryBean implements FactoryBean<Message> {
    String text;

    /**
     * setter를 통한 DI 주입
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * 실제 빈으로 사용될 객체를 직접 생성
     */
    @Override
    public Message getObject() throws Exception {
        return Message.newMessage(this.text);
    }

    @Override
    public Class<?> getObjectType() {
        return Message.class;
    }

    /**
     * getObject() 메서드가 반환하는 객체가 싱글톤인지 알려줌
     */
    @Override
    public boolean isSingleton() {
        return false;
    }
}
