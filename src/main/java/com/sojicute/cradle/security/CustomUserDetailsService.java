package com.sojicute.cradle.security;

import com.sojicute.cradle.domain.User;
import com.sojicute.cradle.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private UserService userService;

    @Override
    public CustomUserDetails  loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        return CustomUserDetails.toCustomUserDetails(user);
    }
}
