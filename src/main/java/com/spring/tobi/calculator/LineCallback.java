package com.spring.tobi.calculator;

public interface LineCallback<T> {

    public T doSomethingWithLine(String line, T value);
}
