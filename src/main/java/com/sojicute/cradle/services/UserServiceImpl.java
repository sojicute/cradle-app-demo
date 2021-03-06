package com.sojicute.cradle.services;

import com.sojicute.cradle.domain.Role;
import com.sojicute.cradle.domain.User;
import com.sojicute.cradle.repository.RoleRepository;
import com.sojicute.cradle.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException("User " + username + " was not found in the database");
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user = findByUsername(username);
        if (user != null ) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User saveUser(User user) {
        Role role = roleRepository.findByName("ROLE_USER");
        user.setRoles(List.of(role));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
