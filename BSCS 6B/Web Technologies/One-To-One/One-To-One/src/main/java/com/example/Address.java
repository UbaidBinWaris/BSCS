package com.example;

import jakarta.persistence.*;

@Entity
@Table(name = "address", schema = "one")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String city;
    @Column(name = "zip_code")
    private String zipCode;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    public Address() {}

    public Address(String street, String city, String zipCode,Student student) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.student = student;
    }

    // Getters & setters
    public Long getId() { return id; }
    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getZipCode() { return zipCode; }
    public void setZipCode(String zipCode) { this.zipCode = zipCode; }
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
