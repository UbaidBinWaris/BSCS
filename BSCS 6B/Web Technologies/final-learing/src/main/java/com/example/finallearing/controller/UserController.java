package com.example.finallearing.controller;

import com.example.finallearing.entity.User;
import com.example.finallearing.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession session,
            Model model,
            RedirectAttributes redirectAttributes) {

        // Validate user credentials
        if (userService.validateUser(username, password)) {
            Optional<User> userOpt = userService.findByUsername(username);

            if (userOpt.isPresent()) {
                User user = userOpt.get();

                // Store user in session
                session.setAttribute("loggedInUser", user);
                session.setAttribute("username", user.getUsername());
                session.setAttribute("fullName", user.getFullName());
                session.setAttribute("role", user.getRole());

                redirectAttributes.addFlashAttribute("successMessage", "Welcome, " + user.getFullName() + "!");
                return "redirect:/dashboard";
            }
        }

        // Login failed
        redirectAttributes.addFlashAttribute("errorMessage", "Invalid username or password!");
        return "redirect:/login";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("successMessage", "You have been logged out successfully!");
        return "redirect:/login";
    }
}

