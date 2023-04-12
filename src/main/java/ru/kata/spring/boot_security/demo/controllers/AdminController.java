package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private final UserServiceImpl userService;

    @Autowired
    public AdminController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String viewAdminPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @GetMapping(value = "/showPageToAddUser")
    public String showPageToAddUser(@ModelAttribute("user") User user) {
        return "new_user";
    }

    @PostMapping(value = "/createNewUser")
    public String createNewUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/new_user";
        }

        userService.addNewUser(user);

        return "redirect:/admin";
    }

//    @GetMapping(value = "/{id}/editUser")
//    public String editUser(@PathVariable("id") long id, Model model) {
//        model.addAttribute("user", userService.getUserById(id));
//        return "edit-user";
//    }
//
//    @PatchMapping(value = "/{id}")
//    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "/edit-user";
//        }
//
//        userService.updateUser(user);
//        return "redirect:/users";
//    }





//    @PostMapping(value = "/save")
//    public String saveUser(@ModelAttribute("user") @Valid User user) {
//        userService.saveNewUser(user);
//
//        return "redirect:/admin";
//    }

//    @PostMapping()
//    public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "/add-user";
//        }
//
//        userService.saveUser(user);
//        return "redirect:/users";
//    }

//    @GetMapping("/edit/{id}")
//    public ModelAndView showEditUserPage(@PathVariable(name = "id") Long id) {
//        ModelAndView mav = new ModelAndView("edit_user");
//        mav.addObject("user", userService.getUserById(id));
//
//        return mav;
//    }




//    @DeleteMapping(value = "/delete/{id}")
//    public String removeUser(@PathVariable("id") Long id) {
//        userService.removeUserById(id);
//
//        return "redirect:/users";
//    }
//
//    @GetMapping(value = "/{id}")
//    public String showUserById(@PathVariable("id") long id, Model model) {
//        model.addAttribute("showUser", userService.getUserById(id));
//        return "show-user-info";
//    }

}