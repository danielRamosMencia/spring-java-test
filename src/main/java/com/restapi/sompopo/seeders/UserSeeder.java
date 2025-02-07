package com.restapi.sompopo.seeders;

import com.restapi.sompopo.entitites.UserEntity;
import com.restapi.sompopo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Verificar si ya existe el usuario admin
        if (userRepository.findByUsername("admin").isEmpty()) {
            UserEntity adminUser = new UserEntity();
            adminUser.setUsername("admin");
            adminUser.setPassword(passwordEncoder.encode("admin"));
            adminUser.setEmail("admin@example.com");
            adminUser.setMsisdn("+504 2222-22222");
            adminUser.setCreatedAt(new Date());

            userRepository.save(adminUser);
            System.out.println("Admin user created.");
        } else {
            System.out.println("Admin user already exists.");
        }
    }
}
