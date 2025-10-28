package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class UserDAOTests {
    static UserDAO userDAO;

    @BeforeAll
    public static void Setup(){
        userDAO =  new UserDAO();
    }

    @Test
    public void insertUserTest(){
        User user = new User("Umair","letmein");
        userDAO.addUser(user);

    }

    @Test
    public void findUserTest(){
        Assertions.assertEquals(1,userDAO.getUserById(1).getId());
    }

    @Test
    public void findAllUserTest(){
        Assertions.assertEquals(1,userDAO.getUserById(1).getId());
    }

    @Test
    public void updateUserTest(){
        User user = new User();
        user.setId(1);
        user.setUsername("Umair");
        user.setPassword("letmein");
        userDAO.updateUser(user);
        Assertions.assertEquals("Umair",userDAO.getUserById(1).getUsername());
        Assertions.assertEquals("letmein",userDAO.getUserById(1).getPassword());
    }

    @Test
    public void deleteUserTest(){
        userDAO.deleteUser(1);
        Assertions.assertNull(userDAO.getUserById(1));
    }

}

