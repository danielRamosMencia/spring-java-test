package com.restapi.sompopo.services;

import com.restapi.sompopo.dtos.AllUsersDto;
import com.restapi.sompopo.dtos.UserDto;
import com.restapi.sompopo.entitites.UserEntity;
import com.restapi.sompopo.repositories.UserRepository;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // For auth
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getUsername());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singleton(authority));
    }

    // CRUD Methdos
    public boolean existsByUserName(String username) {
        return userRepository.existsByUsername(username);
    }

    public List<AllUsersDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> new AllUsersDto(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getMsisdn(),
                        user.getCreatedAt()))
                .toList();
    }

    public void registerUser(UserDto newUserDto) {
        if (this.existsByUserName(newUserDto.getUsername())) {
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

        this.save(user);
    }

    public Boolean updateUser(UserEntity updatedUser, Long id) {
        Optional<UserEntity> storedUser = userRepository.findById(id);

        if (!storedUser.isPresent()) {
            return false;
        }

        UserEntity user = storedUser.get();
        Date updatedAt = new Date(System.currentTimeMillis());
        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());
        user.setMsisdn(updatedUser.getMsisdn());
        user.setUpdatedAt(updatedAt);
        user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        user.setUpdatedAt(updatedAt);

        userRepository.save(user);

        return true;
    }

    public Boolean deleteUser(Long id) {
        Optional<UserEntity> storedUser = userRepository.findById(id);

        if (!storedUser.isPresent()) {
            return false;
        }

        UserEntity user = storedUser.get();
        Date deletedAt = new Date(System.currentTimeMillis());
        user.setDeletedAt(deletedAt);

        userRepository.save(user);

        return true;
    }

    public void save(UserEntity user) {
        userRepository.save(user);
    }
}