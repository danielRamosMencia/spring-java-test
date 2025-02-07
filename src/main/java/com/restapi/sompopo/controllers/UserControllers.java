package com.restapi.sompopo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/users")
public class UserControllers {
    @GetMapping("/")
    public ResponseEntity<String> checkAuth(){
            return ResponseEntity.ok().body("Usuarios");
    }
    
}
