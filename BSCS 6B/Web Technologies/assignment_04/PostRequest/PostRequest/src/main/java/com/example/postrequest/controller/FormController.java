package com.example.postrequest.controller;

import com.example.postrequest.model.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormController {

    // Handles GET requests to /user/form (displays the blank form)
    @GetMapping("/user/form")
    public String showUserForm(Model model) {
        // Adds an empty UserInfo object to the model for the Thymeleaf template
        model.addAttribute("userInfo", new UserInfo());
        return "userForm"; // Maps to userForm.html
    }

    // Handles POST requests to /user/submit (receives form data)
    @PostMapping("/user/submit")
    public String submitUserForm(@ModelAttribute("userInfo") UserInfo userInfo) {
        // Spring binds form fields to the UserInfo object; the object is in the Model.
        System.out.println("Received user info: " + userInfo.toString());
        return "userResult"; // Maps to userResult.html
    }

}
