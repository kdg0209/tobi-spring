package com.spring.tobi.aop;

public class HelloUppercaseProxy implements Hello {

    // 위임할 실체 오브젝트
    // 실체 클래스의 오브젝트인 것은 알지만 다른 프록시를 추가할 수도 있으므로 인터페이스로 접근
    Hello hello;

    public HelloUppercaseProxy(Hello hello) {
        this.hello = hello;
    }

    /**
     * 위임 및 부가기능(대문자로 변경)
     */
    @Override
    public String sayHello(String name) {
        return hello.sayHello(name).toUpperCase();
    }

    @Override
    public String sayHi(String name) {
        return hello.sayHi(name).toUpperCase();
    }

    @Override
    public String sayThankYou(String name) {
        return hello.sayThankYou(name).toUpperCase();
    }
}
