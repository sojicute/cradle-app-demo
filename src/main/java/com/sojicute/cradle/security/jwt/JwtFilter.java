package com.sojicute.cradle.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sojicute.cradle.api.exception.UserNameNotFoundException;
import com.sojicute.cradle.security.CustomUserDetails;
import com.sojicute.cradle.security.CustomUserDetailsService;
import jdk.jfr.ContentType;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Collections;

import static org.springframework.util.StringUtils.hasText;

@Component
public class JwtFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION = "Authorization";

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    public void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {

        try {


            String token = getTokenFromRequest(servletRequest);
            if (token != null && jwtProvider.validateToken(token)) {
                String username = jwtProvider.getLoginFromToken(token);
                UserDetails customUserDetails = customUserDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            } else {
                System.out.println("Cannot set the Security Context");
            }
        } catch (UsernameNotFoundException ex) {



            servletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            servletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);

            PrintWriter writer = servletResponse.getWriter();
            writer.write(ex.getMessage());
            writer.flush();
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader(AUTHORIZATION);
        if (hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }
}
