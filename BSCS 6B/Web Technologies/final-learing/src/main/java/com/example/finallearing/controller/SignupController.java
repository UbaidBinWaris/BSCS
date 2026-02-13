package com.example.finallearing.controller;

import com.example.finallearing.entity.User;
import com.example.finallearing.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignupController {

    private final UserService userService;

    @GetMapping
    public String signupPage(Model model) {
        model.addAttribute("pageTitle", "Sign Up");
        return "signup";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("confirmPassword") String confirmPassword,
            @RequestParam("email") String email,
            @RequestParam("fullName") String fullName,
            RedirectAttributes redirectAttributes) {

        // Validate input
        if (username == null || username.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Username is required!");
            return "redirect:/signup";
        }

        if (password == null || password.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Password is required!");
            return "redirect:/signup";
        }

        if (!password.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Passwords do not match!");
            return "redirect:/signup";
        }

        if (password.length() < 6) {
            redirectAttributes.addFlashAttribute("errorMessage", "Password must be at least 6 characters!");
            return "redirect:/signup";
        }

        if (email == null || email.trim().isEmpty() || !email.contains("@")) {
            redirectAttributes.addFlashAttribute("errorMessage", "Valid email is required!");
            return "redirect:/signup";
        }

        if (fullName == null || fullName.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Full name is required!");
            return "redirect:/signup";
        }

        // Check if username already exists
        if (userService.findByUsername(username).isPresent()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Username already exists!");
            return "redirect:/signup";
        }

        // Create new user
        User newUser = new User();
        newUser.setUsername(username.trim());
        newUser.setPassword(password);
        newUser.setEmail(email.trim());
        newUser.setFullName(fullName.trim());
        newUser.setRole("USER");
        newUser.setEnabled(true);

        // Save user
        userService.saveUser(newUser);

        redirectAttributes.addFlashAttribute("successMessage", "Registration successful! Please login.");
        return "redirect:/login";
    }
}

