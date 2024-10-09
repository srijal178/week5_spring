package com.aiss.studentmgmt.controller;

import com.aiss.studentmgmt.model.User;
import com.aiss.studentmgmt.service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "signup";
    }

    // Process the sign-up form
    @PostMapping("/signup")
    public String registerUserAccount(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/signup?success";
    }
    
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
    
    @PostMapping("/login")
    public String login(HttpSession session, @RequestParam String username) {
        session.setAttribute("user", username);
        return "home";
    }
}
