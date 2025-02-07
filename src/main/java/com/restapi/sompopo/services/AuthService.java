package com.restapi.sompopo.services;

import com.restapi.sompopo.dtos.UserDto;
import com.restapi.sompopo.entitites.UserEntity;
import com.restapi.sompopo.jwt.JwtUtil;

import java.sql.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public AuthService(UserService userService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil,
            AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    public String authenticate(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
                password);

        Authentication authResult = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authResult);
        return jwtUtil.generateToken(authResult);
    }

    public void registerUser(UserDto newUserDto) {
        if (userService.existsByUserName(newUserDto.getUsername())) {
            throw new IllegalArgumentException("El nombre de usuario ya existe");
        }

        Date createdAt = new Date(System.currentTimeMillis());

        UserEntity user = new UserEntity(
                newUserDto.getUsername(),
                passwordEncoder.encode(newUserDto.getPassword()),
                newUserDto.getEmail(),
                newUserDto.getMsisdn(),
                createdAt,
                null,
                null,
                null);

        userService.save(user);
    }
}