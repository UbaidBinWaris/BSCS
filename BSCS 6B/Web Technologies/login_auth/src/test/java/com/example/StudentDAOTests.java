package com.example;

import org.junit.jupiter.api.*;
import java.util.List;

public class StudentDAOTests {
    static StudentDAO studentDAO;
    private int testStudentId;

    @BeforeAll
    public static void setupAll() {
        studentDAO = new StudentDAO();
    }

    @BeforeEach
    public void setupTestStudent() {
        // Create a test student before each test
        Student student = new Student("Test Student", 3.5, 3.7, "test@student.com");
        studentDAO.addStudent(student);
        // Retrieve the inserted student to get the generated ID
        List<Student> students = studentDAO.getAllStudents();
        Student inserted = students.stream()
            .filter(s -> "test@student.com".equals(s.getEmail()))
            .findFirst().orElse(null);
        if (inserted != null) {
            testStudentId = inserted.getStudent_id();
        }
    }

    @AfterEach
    public void cleanupTestStudent() {
        // Clean up after each test
        if (testStudentId > 0) {
            studentDAO.deleteStudent(testStudentId);
        }
    }

    @Test
    public void insertStudentTest() {
        Student student = studentDAO.getStudentById(testStudentId);
        Assertions.assertNotNull(student);
        Assertions.assertEquals("Test Student", student.getName());
    }

    @Test
    public void findStudentTest() {
        Student student = studentDAO.getStudentById(testStudentId);
        Assertions.assertNotNull(student);
        Assertions.assertEquals(testStudentId, student.getStudent_id());
    }

    @Test
    public void updateStudentTest() {
        Student student = studentDAO.getStudentById(testStudentId);
        Assertions.assertNotNull(student);
        student.setName("Updated Name");
        student.setSemester_gpa(3.9);
        student.setCumulative_gpa(3.95);
        student.setEmail("updated@student.com");
        studentDAO.updateStudent(student);
        Student updated = studentDAO.getStudentById(testStudentId);
        Assertions.assertEquals("Updated Name", updated.getName());
        Assertions.assertEquals(3.9, updated.getSemester_gpa());
        Assertions.assertEquals(3.95, updated.getCumulative_gpa());
        Assertions.assertEquals("updated@student.com", updated.getEmail());
    }

    @Test
    public void deleteStudentTest() {
        studentDAO.deleteStudent(testStudentId);
        Student student = studentDAO.getStudentById(testStudentId);
        Assertions.assertNull(student);
        // Set to 0 so @AfterEach doesn't try to delete again
        testStudentId = 0;
    }

    @Test
    public void getAllStudentsTest() {
        List<Student> students = studentDAO.getAllStudents();
        Assertions.assertTrue(students.size() > 0);
    }
}

