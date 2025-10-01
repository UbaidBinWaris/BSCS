package com.example;

public class SalariedEmployee extends Employee {

    private double weeklySalary;



//    @Override
//    public double earning() {
//        return weeklySalary;
//    }


    @Override
    public double earning() {
        return weeklySalary;
    }

    public SalariedEmployee(String name, double weeklySalary) {
        super(name);
        this.weeklySalary = weeklySalary;
    }

    public double getWeeklySalary() {
        return weeklySalary;
    }

//    @Override
//    public String toString() {
//        return "{id: " + getId() +", Name: " + getName() + ", Weekly Salary: " + weeklySalary + "}";
//    }


    @Override
    public String toString() {
        return "{id: " + getId() +", Name: " + getName() + ", Weekly Salary: " + weeklySalary + "}";
    }
}