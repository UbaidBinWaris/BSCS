package com.example.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "<h1>Welcome to Spring Boot!</h1>" +
               "<p>Your application is running successfully.</p>" +
               "<p>This is similar to Jakarta EE but with Spring Boot's embedded server.</p>";
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello from Spring Boot Controller!";
    }
}

