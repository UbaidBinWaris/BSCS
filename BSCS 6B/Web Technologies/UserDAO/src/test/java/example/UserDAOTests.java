package example;

import org.junit.jupiter.api.*;

public class UserDAOTests {
    static UserDAO userDAO;
    private int testUserId;

    @BeforeAll
    public static void Setup(){
        userDAO =  new UserDAO();
    }

    @BeforeEach
    public void setupTestUser() {
        // Create a test user before each test
        User user = new User("Umair","letmein");
        userDAO.addUser(user);
        // Retrieve the inserted user to get the generated ID
        User insertedUser = userDAO.getUserByUsername("Umair");
        if (insertedUser != null) {
            testUserId = insertedUser.getId();
        }
    }

    @AfterEach
    public void cleanupTestUser() {
        // Clean up after each test
        if (testUserId > 0) {
            userDAO.deleteUser(testUserId);
        }
    }

    @Test
    public void insertUserTest(){
        User user = userDAO.getUserById(testUserId);
        Assertions.assertNotNull(user);
        Assertions.assertEquals("Umair", user.getUsername());
    }

    @Test
    public void findUserTest(){
        User user = userDAO.getUserById(testUserId);
        Assertions.assertNotNull(user);
        Assertions.assertEquals(testUserId, user.getId());
    }

    @Test
    public void findAllUserTest(){
        Assertions.assertTrue(userDAO.getAllUsers().size() > 0);
    }

    @Test
    public void updateUserTest(){
        User user = new User();
        user.setId(testUserId);
        user.setUsername("UmairUpdated");
        user.setPassword("newpassword");
        userDAO.updateUser(user);
        User updatedUser = userDAO.getUserById(testUserId);
        Assertions.assertNotNull(updatedUser);
        Assertions.assertEquals("UmairUpdated", updatedUser.getUsername());
        Assertions.assertEquals("newpassword", updatedUser.getPassword());
    }

    @Test
    public void deleteUserTest(){
        userDAO.deleteUser(testUserId);
        Assertions.assertNull(userDAO.getUserById(testUserId));
    }
}
