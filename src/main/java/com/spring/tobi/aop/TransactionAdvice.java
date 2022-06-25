package com.spring.tobi.aop;

import lombok.Setter;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Setter
public class TransactionAdvice implements MethodInterceptor {

    PlatformTransactionManager transactionManager;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        TransactionStatus status = this.transactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            /**
             * proceed() 메서드를 이용히여 콜백을 호출해서 실체 오브젝트의 메서드를 실행한다.
             * 실체 오브젝트 호출 전후로 필요한 부가기능을 넣을 수 있다 현재는 실체 오브젝트의 메서드를 호출한 뒤 커밋을 부가기능으로 사용하고 있다.
             */
            Object ref = invocation.proceed();
            this.transactionManager.commit(status);
            return ref;
        } catch (RuntimeException e) {
            this.transactionManager.rollback(status);
            throw  e;
        }
    }
}
