package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final UserServiceImpl userService;

    private final UserDetailsService userDetailsService;

    public UserController(UserServiceImpl userService, @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping
    public String pageForUser(Model model) {
        String principalName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = (User) userDetailsService.loadUserByUsername(principalName);
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping(value = "/login")
    public String loginPage() {
        return "login";
    }
}
