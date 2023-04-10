package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private final UserServiceImpl service;

    @Autowired
    public AdminController(UserServiceImpl service) {
        this.service = service;
    }

    @RequestMapping
    public String viewAdminPage(Model model) {
        model.addAttribute("users", service.listAll());

        return "admin";
    }

    @RequestMapping("/new")
    public String showNewUserPage(@ModelAttribute("user") User user) {
        return "new_user";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user) {
        service.save(user);

        return "redirect:/admin";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditUserPage(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_user");
        mav.addObject("user", service.get(id));

        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") Long id) {
        service.delete(id);

        return "redirect:/admin";
    }

}