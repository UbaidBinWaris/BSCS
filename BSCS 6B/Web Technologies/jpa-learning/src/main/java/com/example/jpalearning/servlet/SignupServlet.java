package com.example.jpalearning.servlet;

import com.example.jpalearning.dao.UserDAO;
import com.example.jpalearning.entity.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SignupServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String confirmPassword = request.getParameter("confirmPassword");

        // Validation
        if (username == null || username.trim().isEmpty() ||
            password == null || password.trim().isEmpty() ||
            email == null || email.trim().isEmpty()) {
            request.setAttribute("error", "All fields are required!");
            request.getRequestDispatcher("/signup.jsp").forward(request, response);
            return;
        }

        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Passwords do not match!");
            request.getRequestDispatcher("/signup.jsp").forward(request, response);
            return;
        }

        // Check if username already exists
        if (userDAO.findByUsername(username) != null) {
            request.setAttribute("error", "Username already exists!");
            request.getRequestDispatcher("/signup.jsp").forward(request, response);
            return;
        }

        // Check if email already exists
        if (userDAO.findByEmail(email) != null) {
            request.setAttribute("error", "Email already registered!");
            request.getRequestDispatcher("/signup.jsp").forward(request, response);
            return;
        }

        // Create new user
        User newUser = new User(username, password, email);
        User createdUser = userDAO.createUser(newUser);

        if (createdUser != null) {
            request.setAttribute("success", "Account created successfully! Please login.");
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            request.setAttribute("error", "Registration failed. Please try again.");
            request.getRequestDispatcher("/signup.jsp").forward(request, response);
        }
    }
}

