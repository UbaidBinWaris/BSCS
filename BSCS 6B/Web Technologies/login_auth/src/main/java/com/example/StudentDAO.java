package com.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {


    public void addStudent(Student student) {
        String sql = "INSERT INTO students(name, semester_gpa, cumulative_gpa, email) VALUES (?, ?, ?, ?)";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, student.getName());
            ps.setDouble(2, student.getSemester_gpa());
            ps.setDouble(3, student.getCumulative_gpa());
            ps.setString(4, student.getEmail());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int studentId) {
        String sql = "DELETE FROM students WHERE student_id = ?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(Student student) {
        String sql = "UPDATE students SET name = ?, semester_gpa = ?, cumulative_gpa = ?, email = ? WHERE student_id = ?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, student.getName());
            stmt.setDouble(2, student.getSemester_gpa());
            stmt.setDouble(3, student.getCumulative_gpa());
            stmt.setString(4, student.getEmail());
            stmt.setInt(5, student.getStudent_id());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Connection conn = DbUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Student student = new Student();
                student.setStudent_id(rs.getInt("student_id"));
                student.setName(rs.getString("name"));
                student.setSemester_gpa(rs.getDouble("semester_gpa"));
                student.setCumulative_gpa(rs.getDouble("cumulative_gpa"));
                student.setEmail(rs.getString("email"));
                students.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    public Student getStudentById(int studentId) {
        Student student = null;
        String sql = "SELECT * FROM students WHERE student_id = ?";

        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    student = new Student();
                    student.setStudent_id(rs.getInt("student_id"));
                    student.setName(rs.getString("name"));
                    student.setSemester_gpa(rs.getDouble("semester_gpa"));
                    student.setCumulative_gpa(rs.getDouble("cumulative_gpa"));
                    student.setEmail(rs.getString("email"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }

    public Student getStudentByEmail(String email) {
        Student student = null;
        String sql = "SELECT * FROM students WHERE email = ?";

        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    student = new Student();
                    student.setStudent_id(rs.getInt("student_id"));
                    student.setName(rs.getString("name"));
                    student.setSemester_gpa(rs.getDouble("semester_gpa"));
                    student.setCumulative_gpa(rs.getDouble("cumulative_gpa"));
                    student.setEmail(rs.getString("email"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }
}
