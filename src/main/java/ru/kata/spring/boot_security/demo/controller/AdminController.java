package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private final UserServiceImpl userServiceImpl;
    private final RoleServiceImpl roleServiceImpl;

    public AdminController(UserServiceImpl userServiceImpl, RoleServiceImpl roleServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.roleServiceImpl = roleServiceImpl;
    }

    @GetMapping
    public String adminHomePage(Model model) {
        model.addAttribute("allUsers", userServiceImpl.getAllUsers());
        return "home";
    }

    @PostMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleServiceImpl.getAllRoles());
        return "new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") User user) {
        userServiceImpl.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id) {
        List<Role> roleList = roleServiceImpl.getAllRoles();
        model.addAttribute("user", userServiceImpl.getUserById(id));
        model.addAttribute("roles", roleServiceImpl.getAllRoles());
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userServiceImpl.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable("id") int id) {
        userServiceImpl.removeUser(id);
        return "redirect:/admin";
    }
}
