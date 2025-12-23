package com.example.controller;

import com.example.model.UserInfo;
import com.example.service.UserInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormController {

    private final UserInfoService userInfoService;

    // Constructor injection
    public FormController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    // Handles GET requests to /user/form (displays the blank form)
    @GetMapping("/user/form")
    public String showUserForm(Model model) {
        // Adds an empty UserInfo object to the model for the Thymeleaf template
        model.addAttribute("userInfo", new UserInfo());
        return "userForm"; // Maps to userForm.html
    }

    // Handles POST requests to /user/submit (receives form data)
    @PostMapping("/user/submit")
    public String submitUserForm(@ModelAttribute("userInfo") UserInfo userInfo, Model model) {
        // Spring binds form fields to the UserInfo object; the object is in the Model.
        System.out.println("Received user info: " + userInfo.toString());

        // Save the user info to the database
        UserInfo savedUser = userInfoService.saveUserInfo(userInfo);
        System.out.println("Saved to database with ID: " + savedUser.getId());

        // Fetch all users from database and add to model
        model.addAttribute("allUsers", userInfoService.getAllUsers());

        return "userResult"; // Maps to userResult.html
    }

}
