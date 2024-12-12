package com.drama.drama_user.controller;

import com.drama.drama_user.model.User;
import com.drama.drama_user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Endpoint para registrar un usuario
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
    try {
        User newUser = userService.createUser(user);
        return ResponseEntity.ok(newUser);
    } catch (RuntimeException e) {
        return ResponseEntity.badRequest().body("Error al registrar el usuario: " + e.getMessage());
    }
}


    // Endpoint para obtener todos los usuarios
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
