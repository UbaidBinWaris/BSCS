package com.example.getrequest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {
    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name="name", required=false, defaultValue="World") String name,
            Model model // The Model object is used to pass data to the view
    ) {
        // Add an attribute named "name" to the model
        model.addAttribute("name", name);

        // Return the *logical view name* ("greeting")
        // Spring Boot/Thymeleaf resolves this to:
        // src/main/resources/templates/greeting.html
        return "greeting";
    }
}
