package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "AddStudent", value = "/addStudent")
public class AddStudent extends HttpServlet {
    private StudentDAO studentDAO = new StudentDAO();

    String paramName;
    String paramPassword;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Display the add student form
        response.setContentType("text/html");

        paramName = request.getParameter("username");
        paramPassword = request.getParameter("password");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html><head>");
        out.println("<link rel='stylesheet' href='css/style.css'>");
        out.println("<title>Add Student</title></head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<h2>Add New Student</h2>");
        out.println("<form action='addStudent' method='post'>");

        out.println("<div class='form-group'>");
        out.println("<label for='name'>Name:</label>");
        out.println("<input type='text' id='name' name='name' placeholder='Enter student name' required>");
        out.println("</div>");

        out.println("<div class='form-group'>");
        out.println("<label for='semester_gpa'>Semester GPA:</label>");
        out.println("<input type='number' id='semester_gpa' step='0.01' name='semester_gpa' placeholder='0.00' min='0' max='4.0' required>");
        out.println("</div>");

        out.println("<div class='form-group'>");
        out.println("<label for='cumulative_gpa'>Cumulative GPA:</label>");
        out.println("<input type='number' id='cumulative_gpa' step='0.01' name='cumulative_gpa' placeholder='0.00' min='0' max='4.0' required>");
        out.println("</div>");

        out.println("<div class='form-group'>");
        out.println("<label for='email'>Email:</label>");
        out.println("<input type='email' id='email' name='email' placeholder='student@example.com' required>");
        out.println("</div>");

        out.println("<button type='submit'>Add Student</button>");
        out.println("<button type='button' onclick=\"location.href='auth?username=" + paramName +"&password=" + paramPassword +"'\">Cancel</button>");
        out.println("</form>");
        out.println("</div></body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Handle form submission (insert student into DB)
        String name = request.getParameter("name");
        double semesterGpa = Double.parseDouble(request.getParameter("semester_gpa"));
        double cumulativeGpa = Double.parseDouble(request.getParameter("cumulative_gpa"));
        String email = request.getParameter("email");

        Student student = new Student(name, semesterGpa, cumulativeGpa, email);
        studentDAO.addStudent(student);

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html><head>");
        out.println("<link rel='stylesheet' href='css/style.css'>");
        out.println("<title>Success</title></head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<div class='alert alert-success'>");
        out.println("<h2>Student Added Successfully!</h2>");
        out.println("</div>");
        out.println("<button type='button' onclick=\"location.href='auth?username=" +
                paramName +"&password=" + paramPassword +"'\">Go Back to Student List</button>");
        out.println("</div></body></html>");
    }
}
