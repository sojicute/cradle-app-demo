package com.sojicute.cradle.services;

import com.sojicute.cradle.domain.User;

public interface UserService {

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);

    User saveUser(User user);
}
