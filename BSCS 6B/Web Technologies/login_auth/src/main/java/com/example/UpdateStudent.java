package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "UpdateStudent", value = "/updateStudent")
public class UpdateStudent extends HttpServlet {
    private StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        out.println("<html><body>");
        out.println("<h2>Update Student</h2>");
        out.println("<form action='updateStudent' method='post'>");
        out.println("Student ID: <input type='number' name='id' required><br><br>");
        out.println("New Name: <input type='text' name='name' required><br><br>");
        out.println("New Semester GPA: <input type='number' step='0.01' name='semester_gpa' required><br><br>");
        out.println("New Cumulative GPA: <input type='number' step='0.01' name='cumulative_gpa' required><br><br>");
        out.println("New Email: <input type='email' name='email' required><br><br>");
        out.println("<input type='hidden' name='username' value='" + username + "'>");
        out.println("<input type='hidden' name='password' value='" + password + "'>");
        out.println("<input type='submit' value='Update Student'>");
        out.println("<button type='button' onclick=\"location.href='auth?username=" + username + "&password=" + password + "'\">Cancel</button>");
        out.println("</form>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double semesterGpa = Double.parseDouble(request.getParameter("semester_gpa"));
        double cumulativeGpa = Double.parseDouble(request.getParameter("cumulative_gpa"));
        String email = request.getParameter("email");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Create updated student object
        Student student = new Student(name, semesterGpa, cumulativeGpa, email);
        studentDAO.updateStudent(student);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h3>Student updated successfully!</h3>");
        out.println("<button type='button' onclick=\"location.href='auth?username=" + username + "&password=" + password + "'\">Back</button>");
        out.println("</body></html>");
    }
}
