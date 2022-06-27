package com.spring.tobi.user.service;

import com.spring.tobi.user.domain.User;
import lombok.Setter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;

@Setter
public class UserServiceTx implements UserService {
    UserService userService;
    PlatformTransactionManager transactionManager;

    @Override
    public User get(String id) {
        return userService.get(id);
    }

    @Override
    public List<User> getAll() {
        return userService.getAll();
    }

    @Override
    public void deleteAll() {
        userService.deleteAll();
    }

    @Override
    public void update(User user) {
        userService.update(user);
    }

    @Override
    public void add(User user) {
        userService.add(user);
    }

    @Override
    public void upgradeLevels() {
        TransactionStatus status = this.transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            userService.upgradeLevels();
            this.transactionManager.commit(status);
        } catch (RuntimeException e) {
            this.transactionManager.rollback(status);
            throw e;
        }
    }
}
