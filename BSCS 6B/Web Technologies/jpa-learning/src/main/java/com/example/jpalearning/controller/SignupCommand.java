package com.example.jpalearning.controller;

import com.example.jpalearning.dao.UserDAO;
import com.example.jpalearning.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignupCommand implements Command {

    private UserDAO userDAO;

    public SignupCommand() {
        this.userDAO = new UserDAO();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String method = request.getMethod();

        if ("GET".equalsIgnoreCase(method)) {
            return "/signup.jsp";
        } else if ("POST".equalsIgnoreCase(method)) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String confirmPassword = request.getParameter("confirmPassword");

            // Validation
            if (username == null || username.trim().isEmpty() ||
                password == null || password.trim().isEmpty() ||
                email == null || email.trim().isEmpty()) {
                request.setAttribute("error", "All fields are required!");
                return "/signup.jsp";
            }

            if (!password.equals(confirmPassword)) {
                request.setAttribute("error", "Passwords do not match!");
                return "/signup.jsp";
            }

            // Check if username already exists
            if (userDAO.findByUsername(username) != null) {
                request.setAttribute("error", "Username already exists!");
                return "/signup.jsp";
            }

            // Check if email already exists
            if (userDAO.findByEmail(email) != null) {
                request.setAttribute("error", "Email already registered!");
                return "/signup.jsp";
            }

            // Create new user
            User newUser = new User(username, password, email);
            User createdUser = userDAO.createUser(newUser);

            if (createdUser != null) {
                request.setAttribute("success", "Account created successfully! Please login.");
                return "redirect:/app/login";
            } else {
                request.setAttribute("error", "Registration failed. Please try again.");
                return "/signup.jsp";
            }
        }

        return "/signup.jsp";
    }
}

