package com.example.jpalearning.controller;

import com.example.jpalearning.dao.UserDAO;
import com.example.jpalearning.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {

    private UserDAO userDAO;

    public LoginCommand() {
        this.userDAO = new UserDAO();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String method = request.getMethod();

        if ("GET".equalsIgnoreCase(method)) {
            return "/login.jsp";
        } else if ("POST".equalsIgnoreCase(method)) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            // Validation
            if (username == null || username.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {
                request.setAttribute("error", "Username and password are required!");
                return "/login.jsp";
            }

            // Authenticate user
            User user = userDAO.authenticate(username, password);

            if (user != null) {
                // Create session
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setAttribute("username", user.getUsername());

                // Redirect to dashboard
                return "redirect:/dashboard.jsp";
            } else {
                request.setAttribute("error", "Invalid username or password!");
                return "/login.jsp";
            }
        }

        return "/login.jsp";
    }
}

