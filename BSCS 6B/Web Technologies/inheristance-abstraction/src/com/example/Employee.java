package com.example;

public abstract class Employee {
    private String name;
    private static int counter = 0;
    private int id;

    public Employee(String name) {
        this.name = name;
//        counter = counter + 1;
        this.id = ++counter;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public abstract double earning();
}
