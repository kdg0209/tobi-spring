package com.spring.tobi.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UppercaseHandler implements InvocationHandler {
    Object target;

    /**
     * 다이나믹 프록시로부터 전달받은 요청을 다시 실체 오브젝트에 위임해야 하기 때문에
     * 실체 오브젝트를 주입 받는다. (다이나믹 프록시(요청) --> 실체 오브젝트에 위임)
     */
    public UppercaseHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object ref = method.invoke(target, args); // 실체 오브젝트에게 위임
        if (ref instanceof String && method.getName().startsWith("say")) {
            return ((String) ref).toUpperCase();
        }
        return ref;
    }
}
