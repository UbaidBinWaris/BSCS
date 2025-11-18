package com.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {

        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            Person p = new Person();
//        p.setId(1);
            p.setFirstName("Saad");
            p.setLastName("Ur rehman");
            p.setAddress("i8/2");
            p.setMobile("0300943437252");
            session.persist(p);

            // Commit first to ensure the person is saved
            session.getTransaction().commit();

            // Now retrieve the person using the generated ID
            session.beginTransaction();
            Person p1 = session.find(Person.class, p.getId());
            System.out.println(p1);
            session.getTransaction().commit();

//            // OLD CODE - Using deprecated session.get() method
//            Person p1 = (Person) session.get(Person.class, 1);
//            System.out.println(p1);
//            session.getTransaction().commit();
        }
    }
}
//package com.example;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//
//public class Main {
//    public static void main(String[] args) {
//
//        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
//             Session session = sessionFactory.openSession()) {
//
//            session.beginTransaction();
//
//            Person p = new Person();
//        p.setId(1);
//            p.setFirstName("Saad");
//            p.setLastName("Ur rehman");
//            p.setAddress("i8/2");
//            p.setMobile("0300943437252");
//            session.persist(p);
//
//            // Commit first to ensure the person is saved
//            session.getTransaction().commit();
//
//            // Now retrieve the person using the generated ID
//            session.beginTransaction();
//            Person p1 = session.find(Person.class, p.getId());
//            System.out.println(p1);
//            session.getTransaction().commit();
//
//            // OLD CODE - Using deprecated session.get() method
//            Person p1 = (Person) session.get(Person.class, 1);
//            System.out.println(p1);
//            session.getTransaction().commit();
//        }
//    }
//}
