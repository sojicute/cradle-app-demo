package com.sojicute.cradle.api;

import com.sojicute.cradle.domain.User;
import com.sojicute.cradle.security.dto.AuthRequest;
import com.sojicute.cradle.security.dto.AuthResponse;
import com.sojicute.cradle.security.dto.RegistrationRequest;
import com.sojicute.cradle.security.jwt.JwtProvider;
import com.sojicute.cradle.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/register")
    public String registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        User u = new User();
        u.setPassword(registrationRequest.getPassword());
        u.setUsername(registrationRequest.getUsername());
        userService.saveUser(u);
        return "OK";
    }

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        User user = userService.findByUsernameAndPassword(request.getUsername(), request.getPassword());
        if (user == null) {
            throw new UsernameNotFoundException(request.getUsername()+" not found");
        }
        String token = jwtProvider.generateToken(user.getUsername());
        return new AuthResponse(token);
    }
}
