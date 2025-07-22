package com.magu.bank.controllers;

import com.magu.bank.dtos.UserDto;
import com.magu.bank.models.User;
import com.magu.bank.repositories.UserRepository;
import com.magu.bank.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getUsers());
    }

    @PostMapping
    public ResponseEntity<User> postUser(@RequestBody UserDto dto) {
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.validateUser(user));
    }

}
