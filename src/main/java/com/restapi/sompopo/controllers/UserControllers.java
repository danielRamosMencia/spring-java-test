package com.restapi.sompopo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.restapi.sompopo.services.UserService;
import com.restapi.sompopo.dtos.AllUsersDto;
import com.restapi.sompopo.dtos.UserDto;
import com.restapi.sompopo.entitites.UserEntity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserControllers {
    
    private final UserService userService;

    @Autowired
    public UserControllers(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public ResponseEntity<List<AllUsersDto>> getAllUsers() {
        List<AllUsersDto> userDtos = userService.getAllUsers();
        return ResponseEntity.ok(userDtos);
    }

    @PostMapping()
    public ResponseEntity<String> register(@RequestBody UserDto newUserDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Revise los campos");
        }
        try {
            userService.registerUser(newUserDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Registrado");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<String> putMethodName(@PathVariable Long id, @RequestBody UserEntity entity,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Revise los campos");
        }
        try {
            Boolean response = userService.updateUser(entity, id);

            if (response) {
                return ResponseEntity.ok().body("Actualizado");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteMethodName(@PathVariable Long id) {
        try {
            Boolean response = userService.deleteUser(id);
            if (response) {
                return ResponseEntity.ok().body("Eliminado");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
