package com.example.dao;

import com.example.entity.Product;
import com.example.entity.User;
import com.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.util.List;

public class ProductDAO {
    
    // Save a new product
    public void saveProduct(Product product) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
            System.out.println("Product saved successfully!");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    
    // Update an existing product
    public void updateProduct(Product product) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(product);
            transaction.commit();
            System.out.println("Product updated successfully!");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    
    // Delete a product
    public void deleteProduct(Long productId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Product product = session.get(Product.class, productId);
            if (product != null) {
                session.delete(product);
                System.out.println("Product deleted successfully!");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    
    // Get product by ID
    public Product getProductById(Long productId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Product.class, productId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Get all products
    public List<Product> getAllProducts() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Product", Product.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Find products by price range
    public List<Product> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Product> query = session.createQuery(
                "FROM Product WHERE price BETWEEN :minPrice AND :maxPrice", Product.class);
            query.setParameter("minPrice", minPrice);
            query.setParameter("maxPrice", maxPrice);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Find products by user
    public List<Product> getProductsByUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Product> query = session.createQuery("FROM Product WHERE createdBy = :user", Product.class);
            query.setParameter("user", user);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
