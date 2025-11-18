package com.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        Student student = new Student("zubair","zubair@szabist-isb.edu.pk");
        Address address = new Address("steert 7","Islamabad","44000",student);

        session.persist(address);// i am persisting owning entity


        session.getTransaction().commit();
    }
}
