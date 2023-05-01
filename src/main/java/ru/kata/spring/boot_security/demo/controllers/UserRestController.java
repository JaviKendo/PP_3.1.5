package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private final UserServiceImpl userServiceImp;

    public UserRestController(UserServiceImpl userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserInfoById(@PathVariable Long id) {
        return ResponseEntity.ok(userServiceImp.getUserById(id));
    }

    @GetMapping("/getUserInfo")
    public ResponseEntity<User> getUserInfo(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(user);
    }

}