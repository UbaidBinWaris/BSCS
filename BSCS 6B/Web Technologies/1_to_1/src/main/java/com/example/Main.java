package com.example;

import com.example.dao.ProductDAO;
import com.example.dao.UserDAO;
import com.example.entity.Product;
import com.example.entity.User;
import com.example.util.HibernateUtil;

import java.math.BigDecimal;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        // Create DAO instances for easy database operations
        UserDAO userDAO = new UserDAO();
        ProductDAO productDAO = new ProductDAO();

        System.out.println("=== Hibernate Easy Methods Demo ===\n");

        // 1. CREATE USERS (Easy Method)
        System.out.println("1. Creating Users...");
        User user1 = new User("john_doe", "john@example.com", "password123", "John Doe");
        User user2 = new User("jane_smith", "jane@example.com", "password456", "Jane Smith");

        userDAO.saveUser(user1);
        userDAO.saveUser(user2);
        System.out.println();

        // 2. READ ALL USERS (Easy Method)
        System.out.println("2. Getting All Users...");
        List<User> allUsers = userDAO.getAllUsers();
        for (User user : allUsers) {
            System.out.println(user);
        }
        System.out.println();

        // 3. FIND USER BY USERNAME (Easy Method)
        System.out.println("3. Finding User by Username...");
        User foundUser = userDAO.getUserByUsername("john_doe");
        if (foundUser != null) {
            System.out.println("Found user: " + foundUser);
        }
        System.out.println();

        // 4. CREATE PRODUCTS (Easy Method)
        System.out.println("4. Creating Products...");
        Product product1 = new Product("Laptop", "Gaming Laptop", new BigDecimal("999.99"), 10, foundUser);
        Product product2 = new Product("Mouse", "Wireless Mouse", new BigDecimal("25.50"), 50, foundUser);
        Product product3 = new Product("Keyboard", "Mechanical Keyboard", new BigDecimal("75.00"), 30, foundUser);

        productDAO.saveProduct(product1);
        productDAO.saveProduct(product2);
        productDAO.saveProduct(product3);
        System.out.println();

        // 5. READ ALL PRODUCTS (Easy Method)
        System.out.println("5. Getting All Products...");
        List<Product> allProducts = productDAO.getAllProducts();
        for (Product product : allProducts) {
            System.out.println(product);
        }
        System.out.println();

        // 6. UPDATE USER (Easy Method)
        System.out.println("6. Updating User...");
        if (foundUser != null) {
            foundUser.setFullName("John Doe Updated");
            userDAO.updateUser(foundUser);
        }
        System.out.println();

        // 7. FIND PRODUCTS BY PRICE RANGE (Easy Method)
        System.out.println("7. Finding Products by Price Range ($20 - $100)...");
        List<Product> productsInRange = productDAO.getProductsByPriceRange(
            new BigDecimal("20.00"), new BigDecimal("100.00"));
        for (Product product : productsInRange) {
            System.out.println(product);
        }
        System.out.println();

        // 8. FIND PRODUCTS BY USER (Easy Method)
        System.out.println("8. Finding Products by User...");
        List<Product> userProducts = productDAO.getProductsByUser(foundUser);
        for (Product product : userProducts) {
            System.out.println(product);
        }
        System.out.println();

        // 9. DELETE PRODUCT (Easy Method)
        System.out.println("9. Deleting a Product...");
        if (!allProducts.isEmpty()) {
            productDAO.deleteProduct(allProducts.get(0).getId());
        }
        System.out.println();

        // 10. FINAL CHECK - Show remaining products
        System.out.println("10. Final Product List...");
        List<Product> finalProducts = productDAO.getAllProducts();
        for (Product product : finalProducts) {
            System.out.println(product);
        }

        // Close Hibernate
        HibernateUtil.shutdown();
        System.out.println("\nHibernate session closed. Demo completed!");
    }
}
