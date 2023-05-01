package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public AdminRestController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userServiceImpl.getAllUsers());
    }

    @PostMapping("/addNewUser")
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        userServiceImpl.addNewUser(user);

        return ResponseEntity.ok(user);
    }

    @PatchMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        userServiceImpl.updateUser(user);

        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/removeUser/{id}")
    public ResponseEntity<String> removeUser(@PathVariable Long id) {
        userServiceImpl.removeUserById(id);

        return ResponseEntity.ok(String.format("User with ID = %d was delete!", id));
    }

}