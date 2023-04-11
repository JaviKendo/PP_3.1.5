package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import java.security.Principal;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{id}")
    public String showUserProfile(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("showUserProfile",
                userService.getUserRepository().getById(id));
        return "user_info";
    }

    @GetMapping()
    public String showUserProfile(Model model, Principal principal) {
        model.addAttribute("showUserProfile",
                userService.getUserRepository().getUserByUsername(principal.getName()).get());
        return "user_info";
    }

}