package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class AccessController {

    @GetMapping("/403")
    public String error403() {
        return "403";
    }

}