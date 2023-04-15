package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import javax.validation.Valid;
import java.security.Principal;

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
    public String viewAdminPage(Model model, Principal principal) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        model.addAttribute("userInf", userService.getUserById(user.getId()));
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleService.getAllRoles());

        return "admin";
    }

    @PostMapping(value = "/createNewUser")
    public String createNewUser(@ModelAttribute("user") @Valid User user) {
        userService.addNewUser(user);

        return "redirect:/admin";
    }

    @PatchMapping(value = "/saveUpdatedUser/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user) {
        userService.updateUser(user);

        return "redirect:/admin";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String removeUser(@PathVariable("id") Long id) {
        userService.removeUserById(id);

        return "redirect:/admin";
    }

}