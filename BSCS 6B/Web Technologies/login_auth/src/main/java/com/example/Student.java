package com.example;

public class Student {
    private int student_id;
    private String name;
    private double semester_gpa;
    private double cumulative_gpa;
    private String email;

    public Student() {
    }

    public Student( String name, double semester_gpa, double cumulative_gpa, String email) {
        this.name = name;
        this.semester_gpa = semester_gpa;
        this.cumulative_gpa = cumulative_gpa;
        this.email = email;
    }


    public Student getStudent() {
        return this;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSemester_gpa() {
        return semester_gpa;
    }

    public void setSemester_gpa(double semester_gpa) {
        this.semester_gpa = semester_gpa;
    }

    public double getCumulative_gpa() {
        return cumulative_gpa;
    }

    public void setCumulative_gpa(double cumulative_gpa) {
        this.cumulative_gpa = cumulative_gpa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "student_id=" + student_id +
                ", name='" + name + '\'' +
                ", semester_gpa=" + semester_gpa +
                ", cumulative_gpa=" + cumulative_gpa +
                ", email='" + email + '\'' +
                '}';
    }
}
