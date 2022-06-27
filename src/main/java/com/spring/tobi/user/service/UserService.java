package com.spring.tobi.user.service;

import com.spring.tobi.user.domain.User;

import java.util.List;

public interface UserService {

    User get(String id);
    List<User> getAll();
    void deleteAll();
    void update(User user);
    void add(User user);
    void upgradeLevels();
}
