package com.example.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        try {
            // You can switch between configurations here
            // For H2 (default): use "hibernate.cfg.xml"
            // For PostgreSQL: use "hibernate-postgresql.cfg.xml"

            String configFile = "hibernate.cfg.xml"; // Default H2
            // String configFile = "hibernate-postgresql.cfg.xml"; // Uncomment for PostgreSQL

            sessionFactory = new Configuration()
                .configure(configFile)
                .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }

    // Helper method to switch to PostgreSQL
    public static void usePostgreSQL() {
        try {
            if (sessionFactory != null) {
                sessionFactory.close();
            }
            sessionFactory = new Configuration()
                .configure("hibernate-postgresql.cfg.xml")
                .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to switch to PostgreSQL: " + ex);
            throw new RuntimeException(ex);
        }
    }
}
