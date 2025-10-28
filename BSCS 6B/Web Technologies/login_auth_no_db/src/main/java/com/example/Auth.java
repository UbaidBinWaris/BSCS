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

        PrintWriter out = response.getWriter();

        // CSS styles for beautiful design
        String styles = "<style>\n" +
                "* { margin: 0; padding: 0; box-sizing: border-box; }\n" +
                "body {\n" +
                "    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\n" +
                "    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);\n" +
                "    min-height: 100vh;\n" +
                "    display: flex;\n" +
                "    justify-content: center;\n" +
                "    align-items: center;\n" +
                "    padding: 20px;\n" +
                "}\n" +
                ".container {\n" +
                "    background: white;\n" +
                "    border-radius: 20px;\n" +
                "    box-shadow: 0 20px 60px rgba(0, 0, 0, 0.3);\n" +
                "    padding: 60px 50px;\n" +
                "    max-width: 500px;\n" +
                "    width: 100%;\n" +
                "    text-align: center;\n" +
                "    animation: slideIn 0.5s ease-out;\n" +
                "}\n" +
                "@keyframes slideIn {\n" +
                "    from { opacity: 0; transform: translateY(-30px); }\n" +
                "    to { opacity: 1; transform: translateY(0); }\n" +
                "}\n" +
                ".success-icon, .error-icon {\n" +
                "    width: 80px;\n" +
                "    height: 80px;\n" +
                "    margin: 0 auto 30px;\n" +
                "    border-radius: 50%;\n" +
                "    display: flex;\n" +
                "    align-items: center;\n" +
                "    justify-content: center;\n" +
                "    font-size: 40px;\n" +
                "}\n" +
                ".success-icon {\n" +
                "    background: linear-gradient(135deg, #11998e 0%, #38ef7d 100%);\n" +
                "    color: white;\n" +
                "}\n" +
                ".error-icon {\n" +
                "    background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);\n" +
                "    color: white;\n" +
                "}\n" +
                "h1 {\n" +
                "    font-size: 32px;\n" +
                "    margin-bottom: 15px;\n" +
                "    color: #333;\n" +
                "}\n" +
                ".success h1 { color: #11998e; }\n" +
                ".error h1 { color: #f5576c; }\n" +
                "h4 {\n" +
                "    font-size: 18px;\n" +
                "    font-weight: 400;\n" +
                "    color: #666;\n" +
                "    line-height: 1.6;\n" +
                "}\n" +
                ".username {\n" +
                "    display: inline-block;\n" +
                "    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);\n" +
                "    -webkit-background-clip: text;\n" +
                "    -webkit-text-fill-color: transparent;\n" +
                "    background-clip: text;\n" +
                "    font-weight: 600;\n" +
                "}\n" +
                "</style>\n";

        if (Objects.equals(name, admin_user) && pass.equals(admin_pass)) {
            out.println("<html><head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<title>Login Success</title>");
            out.println(styles);
            out.println("</head><body>");
            out.println("<div class='container success'>");
            out.println("<div class='success-icon'>✓</div>");
            out.println("<h1>Welcome back, <span class='username'>" + name + "</span>!</h1>");
            out.println("<h4>You have successfully logged in.</h4>");
            out.println("</div>");
            out.println("</body></html>");
            return;
        } else {
            out.println("<html><head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<title>Login Failed</title>");
            out.println(styles);
            out.println("</head><body>");
            out.println("<div class='container error'>");
            out.println("<div class='error-icon'>✕</div>");
            out.println("<h1>Invalid Username or Password</h1>");
            out.println("<h4>Please check your credentials and try again.</h4>");
            out.println("</div>");
            out.println("</body></html>");
            return;
        }
    }

    public void destroy() {
    }
}