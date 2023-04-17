package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private final UserServiceImpl userService;
    private final RoleService roleService;

    public AdminController(UserServiceImpl userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String getAdminPage(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("userInf", user);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleService.getAllRoles());

        return "admin";
    }

    @PostMapping(value = "/createNewUser")
    public String createNewUser(@ModelAttribute("user") User user) {
        userService.addNewUser(user);

        return "redirect:/admin";
    }

    @PatchMapping(value = "/saveUpdatedUser/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);

        return "redirect:/admin";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String removeUser(@PathVariable("id") Long id) {
        userService.removeUserById(id);

        return "redirect:/admin";
    }

}