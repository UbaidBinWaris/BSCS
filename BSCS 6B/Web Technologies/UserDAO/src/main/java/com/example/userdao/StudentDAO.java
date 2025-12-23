package com.example.userdao;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public List<Student> getAllStudents() {
        // Return a hardcoded list for now
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Alice", 3.5, 3.6, "alice@example.com"));
        students.add(new Student(2, "Bob", 3.2, 3.4, "bob@example.com"));
        return students;
    }
}
package com.example.userdao;

public class Student {
    private int student_id;
    private String name;
    private double semester_gpa;
    private double cumulative_gpa;
    private String email;

    public Student(int student_id, String name, double semester_gpa, double cumulative_gpa, String email) {
        this.student_id = student_id;
        this.name = name;
        this.semester_gpa = semester_gpa;
        this.cumulative_gpa = cumulative_gpa;
        this.email = email;
    }

    public int getStudent_id() { return student_id; }
    public String getName() { return name; }
    public double getSemester_gpa() { return semester_gpa; }
    public double getCumulative_gpa() { return cumulative_gpa; }
    public String getEmail() { return email; }
}

