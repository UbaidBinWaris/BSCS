//    package com.example;
//
//    import java.io.*;
//
//    import jakarta.servlet.http.*;
//    import jakarta.servlet.annotation.*;
//
//    @WebServlet(name = "helloServlet", value = "/auth")
//    public class Auth extends HttpServlet {
//        private String message;
//
//        public void init() {
//            message = "Hello World!";
//        }
//
//        public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//            response.setContentType("text/html");
//
//            String name = request.getParameter("username");
//            String pass = request.getParameter("password");
//
//            // Hello
//            PrintWriter out = response.getWriter();
//            out.println("<html><body>");
//            out.println("<h1>" + message + "</h1>");
//            out.println("<h2>Username: " + name + "</h2>");
//            out.println("<h2>Password: " + pass + "</h2>");
//            out.println("</body></html>");
//        }
//
//        public void destroy() {
//        }
//    }

package com.example.userdao;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "helloServlet", value = "/auth")
public class Auth extends HttpServlet {

    //    String admin_user = "Ubaid";
    //    String admin_pass = "Ubaid123";
    private User findUser;
    private UserDAO userDAO = new UserDAO();

//            @Override
//            public void init() {
//                User admin;
//                admin = new User("Ubaid", "Ubaid123");
//                userDAO = new UserDAO();
//                userDAO.addUser(admin);
//
//                String name = request.getParameter("username");
//                String pass = request.getParameter("password");
//
//                User testUser = new User();
//                testUser = userDAO.getUserByUsername("admin");
//
//                testUser = userDAO.getUserById(1);
//                System.out.println("Test User ID: " + testUser.getId());
//                System.out.println("Test User Username: " + testUser.getUsername());
//                System.out.println("Test User Password: " + testUser.getPassword());
//
//                Student student = new Student( "Ubaid", 3.5, 3.6, "ubaid@gmail.com");
//                StudentDAO studentDAO = new StudentDAO();
//                studentDAO.addStudent(student);
//
//                System.out.println("Student added successfully!");
//            }

//            public  User authentication =userDAO.getUserById(admin.getId());
//            public String username = authentication.getUsername();
//            public String password = authentication.getPassword();
//
//            System.out.println("my admin username is : " );
//            System.out.println("my admin password is : " );

    //  Function to validate user and generate proper response
    public void validateUser(String username, String password, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        // Check if username/password fields are missing or empty
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            out.println("<!DOCTYPE html>");
            out.println("<html><head>");
            out.println("<link rel='stylesheet' href='css/style.css'>");
            out.println("<title>Error</title></head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<div class='alert alert-error'>");
            out.println("<h2>Please provide both username and password.</h2>");
            out.println("</div>");
            out.println("<a href='index.html' class='btn'>Go Back</a>");
            out.println("</div></body></html>");
            return;
        }

        // Try to find user in DB
        findUser = userDAO.getUserByUsername(username);

        // If no user found
        if (findUser == null) {
            out.println("<!DOCTYPE html>");
            out.println("<html><head>");
            out.println("<link rel='stylesheet' href='css/style.css'>");
            out.println("<title>Login Failed</title></head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<div class='alert alert-error'>");
            out.println("<h2>Invalid Username or Password</h2>");
            out.println("</div>");
            out.println("<a href='index.html' class='btn'>Go Back</a>");
            out.println("</div></body></html>");
            return;
        }

        // If user exists, validate credentials
        if (Objects.equals(username, findUser.getUsername()) &&
                Objects.equals(password, findUser.getPassword())) {

            StudentDAO studentDAO = new StudentDAO();
            List<Student> students = studentDAO.getAllStudents();

            out.println("<!DOCTYPE html>");
            out.println("<html><head>");
            out.println("<link rel='stylesheet' href='css/style.css'>");
            out.println("<title>Dashboard</title></head>");
            out.println("<body>");
            out.println("<div class='container' style='max-width: 1200px;'>");
            out.println("<h1>Welcome back, " + username + "!</h1>");
            out.println("<p class='text-center mb-20'>You have successfully logged in.</p>");

            out.println("<div class='nav'>");
            out.println("<a href='addStudent?username=" + username + "&password=" + password + "'>Add New Student</a>");
            out.println("<a href='updateStudent?username=" + username + "&password=" + password + "'>Update Student</a>");
            out.println("<a href='deleteStudent?username=" + username + "&password=" + password + "'>Delete Student</a>");
            out.println("<a href='index.html'>Logout</a>");
            out.println("</div>");

            out.println(generateStudentTable(students));
            out.println("</div></body></html>");

        } else {
            out.println("<!DOCTYPE html>");
            out.println("<html><head>");
            out.println("<link rel='stylesheet' href='css/style.css'>");
            out.println("<title>Login Failed</title></head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<div class='alert alert-error'>");
            out.println("<h2>Invalid Username or Password</h2>");
            out.println("</div>");
            out.println("<a href='index.html' class='btn'>Go Back</a>");
            out.println("</div></body></html>");
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        //            String name = request.getParameter("username");
        //            String pass = request.getParameter("password");

        String paramName = request.getParameter("username");
        String paramPassword = request.getParameter("password");




        // Use validation function
        validateUser(paramName, paramPassword, response);
    }

    private String generateStudentTable(List<Student> students) {
        StringBuilder sb = new StringBuilder();
        sb.append("<h3>All Students</h3>");
        sb.append("<table border='1' cellpadding='8' cellspacing='0'>");
        sb.append("<tr><th>ID</th><th>Name</th><th>Semester GPA</th><th>Cumulative GPA</th><th>Email</th></tr>");

        for (Student s : students) {
            sb.append("<tr>")
                    .append("<td>").append(s.getStudent_id()).append("</td>")
                    .append("<td>").append(s.getName()).append("</td>")
                    .append("<td>").append(s.getSemester_gpa()).append("</td>")
                    .append("<td>").append(s.getCumulative_gpa()).append("</td>")
                    .append("<td>").append(s.getEmail()).append("</td>")
                    .append("</tr>");
        }

        sb.append("</table>");
        return sb.toString();
    }


    //            if (Objects.equals(name, admin.getUsername()) && pass.equals(admin.getPassword())) {
    //                PrintWriter out = response.getWriter();
    //                out.println("<html><body>");
    //                out.println("<h1>Welcome back, " + name +"</h1>");
    //                out.println("<h4>You have successfully logged in.</h4>");
    //                out.println("</body></html>");
    //                return;
    //            } else {
    //
    //                PrintWriter out = response.getWriter();
    //                out.println("<html><body>");
    //                out.println("<h1>Invalid Username or Password</h1>");
    //                out.println("</body></html>");
    //                return;
    //            }

    //            // Hello
    //            PrintWriter out = response.getWriter();
    //            out.println("<html><body>");
    //            out.println("<h1>Hello From Responce</h1>");
    //            out.println("<h2>Username: " + paramName + "</h2>");
    //            out.println("<h2>Password: " + paramPassword + "</h2>");
    //            out.println("</body></html>");
    //        }

    //        public void destroy() {
    //        }
}


//
//package com.example;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.Objects;
//import jakarta.servlet.http.*;
//import jakarta.servlet.annotation.*;
//
//@WebServlet(name = "AuthServlet", value = "/auth")
//public class Auth extends HttpServlet {
//
//    private User admin;
//    private UserDAO userDAO;
//
//    @Override
//    public void init() {
//        admin = new User("admin", "admin123");
//        userDAO = new UserDAO();
//        userDAO.addUser(admin);
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setContentType("text/html");
//
//        String name = request.getParameter("username");
//        String pass = request.getParameter("password");
//
//        PrintWriter out = response.getWriter();
//        out.println("<html><body>");
//
//        if (Objects.equals(name, admin.getUsername()) && Objects.equals(pass, admin.getPassword())) {
//            out.println("<h1>Welcome back, " + name + "!</h1>");
//            out.println("<h4>You have successfully logged in.</h4>");
//        } else {
//            out.println("<h1>Invalid Username or Password</h1>");
//        }
//
//        out.println("</body></html>");
//    }
//
//    @Override
//    public void destroy() {
//        // Cleanup if needed
//    }
//}
