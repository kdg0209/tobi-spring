package com.spring.tobi.user.service;

import com.spring.tobi.user.domain.User;

public interface UserService {

    void add(User user);
    void upgradeLevels();
}
