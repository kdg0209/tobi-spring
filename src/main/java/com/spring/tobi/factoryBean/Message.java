package com.spring.tobi.factoryBean;

import lombok.Getter;

public class Message {
    String text;

    private Message(String text) {
        this.text = text;
    }

    public static Message newMessage(String text) {
        return new Message(text);
    }

    public String getText() {
        return text;
    }
}
