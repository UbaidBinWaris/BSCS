    package com.example;

    import java.io.IOException;
    import java.io.PrintWriter;
    import jakarta.servlet.*;
    import jakarta.servlet.http.*;
    import jakarta.servlet.annotation.*;
    @WebServlet(name = "DeleteStudent", value = "/deleteStudent")
    public class DeleteStudent extends HttpServlet {
        private StudentDAO studentDAO = new StudentDAO();

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            out.println("<html><body>");
            out.println("<h2>Delete Student</h2>");
            out.println("<form action='deleteStudent' method='post'>");
            out.println("Student ID: <input type='number' name='id' required><br><br>");
            out.println("<input type='hidden' name='username' value='" + username + "'>");
            out.println("<input type='hidden' name='password' value='" + password + "'>");
            out.println("<input type='submit' value='Delete'>");
            out.println("<button type='button' onclick=\"location.href='auth?username=" + username +"&password=" + password +"'\">Cancel</button>");
            out.println("</form>");
            out.println("</body></html>");
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
            int id = Integer.parseInt(request.getParameter("id"));
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            studentDAO.deleteStudent(id);

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h3>Student deleted successfully!</h3>");
            out.println("<button type='button' onclick=\"location.href='auth?username=" + username + "&password=" + password + "'\">Back</button>");
            out.println("</body></html>");
        }
    }
