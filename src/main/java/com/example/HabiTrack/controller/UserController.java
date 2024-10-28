package com.example.HabiTrack.controller;

import com.example.HabiTrack.model.User;
import com.example.HabiTrack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.crypto.password.PasswordEncoder;
@Controller
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Renders login page
    }

    @GetMapping("/signup")
    public String showSignupPage(Model model) {
        model.addAttribute("user", new User()); // Prepares an empty User object for form binding
        return "signup"; // Renders signup page
    }

    @PostMapping("/signup")
    public String processSignup(@ModelAttribute("user") User user, Model model) {
        if (userService.findByUsername(user.getUsername()) != null) {
            model.addAttribute("errorMessage", "Username already taken!");
            return "signup";
        }

        // Encode the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);

        model.addAttribute("successMessage", "Account created successfully!");
        return "redirect:/login";
    }


    @GetMapping("/profile")
    public String showUserProfile(Model model) {
        // Add profile-related data if needed
        return "profile"; // Renders profile page
    }
}
