package com.example.jpalearning.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FrontController extends HttpServlet {

    private Map<String, Command> commands;

    @Override
    public void init() throws ServletException {
        commands = new HashMap<>();
        commands.put("/login", new LoginCommand());
        commands.put("/signup", new SignupCommand());
        commands.put("/logout", new LogoutCommand());
        commands.put("/dashboard", new DashboardCommand());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getPathInfo();

        // Handle null or empty path
        if (path == null || path.equals("/")) {
            response.sendRedirect(request.getContextPath() + "/app/login");
            return;
        }

        // Get the command for the path
        Command command = commands.get(path);

        if (command == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND,
                "The requested resource [" + path + "] is not available");
            return;
        }

        // Execute the command
        String view = command.execute(request, response);

        // Handle the result
        if (view == null) {
            return; // Command handled the response
        }

        if (view.startsWith("redirect:")) {
            // Redirect
            String redirectUrl = view.substring("redirect:".length());
            if (redirectUrl.startsWith("/")) {
                redirectUrl = request.getContextPath() + redirectUrl;
            }
            response.sendRedirect(redirectUrl);
        } else {
            // Forward to JSP
            request.getRequestDispatcher(view).forward(request, response);
        }
    }
}

