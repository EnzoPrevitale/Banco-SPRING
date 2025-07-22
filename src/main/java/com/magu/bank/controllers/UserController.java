package com.magu.bank.controllers;

import com.magu.bank.dtos.UserDto;
import com.magu.bank.models.User;
import com.magu.bank.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    // Obter todos
    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getUsers());
    }

    // Obter por ID
    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable Integer id) {
        Optional<User> user = service.getUser(id);
        if(user.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(user.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
    }

    // Salvar novo
    @PostMapping
    public ResponseEntity<User> postUser(@RequestBody UserDto dto) {
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.encryptPassword(user));
    }

    // Editar existente
    @PutMapping("/{id}")
    public ResponseEntity putUser(@PathVariable Integer id, @RequestBody UserDto dto) {
        Optional<User> user = service.getUser(id);
        if(user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        }
        BeanUtils.copyProperties(dto, user.get());
        return ResponseEntity.status(HttpStatus.OK).body(service.encryptPassword(user.get()));
    }

    // Apagar registro
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteUser(id));
    }
}
