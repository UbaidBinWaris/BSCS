package com.example;

import java.io.*;
import java.util.Objects;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/auth")
public class Auth extends HttpServlet {


    String admin_user = "Ubaid";
    String admin_pass = "Ubaid123";


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String name = request.getParameter("username");
        String pass = request.getParameter("password");

        if (Objects.equals(name, admin_user) && pass.equals(admin_pass)) {
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>Welcome back, " + name +"</h1>");
            out.println("<h4>You have successfully logged in.</h4>");
            out.println("</body></html>");
            return;
        } else {

            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>Invalid Username or Password</h1>");
            out.println("</body></html>");
            return;
        }

        // Hello
//        PrintWriter out = response.getWriter();
//        out.println("<html><body>");
//        out.println("<h1>Hello From Responce</h1>");
//        out.println("<h2>Username: " + name + "</h2>");
//        out.println("<h2>Password: " + pass + "</h2>");
//        out.println("</body></html>");
    }

    public void destroy() {
    }
}