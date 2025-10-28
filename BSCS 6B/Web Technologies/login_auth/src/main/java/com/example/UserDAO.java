//package com.example;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserDAO {
//    private Connection connection;
//
//    public UserDAO() {
//        this.connection = DbUtil.getConnection();
//    }
//
//    public void addUser(User user) {
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "insert into users(username,password) values (?, ? )");
//            // Parameters start with 1
//            preparedStatement.setString(1, user.getUsername());
//            preparedStatement.setString(2, user.getPassword());
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void deleteUser(int userId) {
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "delete from users where id=?");
//            // Parameters start with 1
//            preparedStatement.setInt(1, userId);
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void updateUser(User user) {
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "update users set username=?, password=? where id=?");
//            // Parameters start with 1
//            preparedStatement.setString(1, user.getUsername());
//            preparedStatement.setString(2, user.getPassword());
//            preparedStatement.setInt(3, user.getId());
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public List<User> getAllBooks() {
//        ResultSet rs = null;
//        List<User> users = new ArrayList<User>();
//        try {
//            Statement statement = connection.createStatement();
//            rs = statement.executeQuery("select * from users");
//            while (rs.next()) {
//                User user = new User();
//                user.setId(rs.getInt("id"));
//                user.setUsername(rs.getString("username"));
//                user.setPassword(rs.getString("password"));
//                users.add(user);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally{
//            try { rs.close();} catch (SQLException e) {throw new RuntimeException(e);}
//            // close resultset
//            // close statememt
//            // close connection in the case of connection pooliing
//        }
//
//        return users;
//    }
//
//    public User getUserById(int userId) {
//        User user = new User();
//        try {
//            PreparedStatement preparedStatement = connection.
//                    prepareStatement("select * from users where id=?");
//            preparedStatement.setInt(1, userId);
//            ResultSet rs = preparedStatement.executeQuery();
//
//            if (rs.next()) {
//                user.setId(rs.getInt("id"));
//                user.setUsername(rs.getString("username"));
//                user.setPassword(rs.getString("password"));
//            }else{
//                user=null;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return user;
//    }
//
//}



//package com.example;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserDAO {
//
//    public void addUser(User user) {
//        String sql = "INSERT INTO users(username, password) VALUES (?, ?)";
//        try (Connection conn = DbUtil.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setString(1, user.getUsername());
//            ps.setString(2, user.getPassword());
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void deleteUser(int userId) {
//        String sql = "DELETE FROM users WHERE id=?";
//        try (Connection conn = DbUtil.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setInt(1, userId);
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void updateUser(User user) {
//        String sql = "UPDATE users SET username=?, password=? WHERE id=?";
//        try (Connection conn = DbUtil.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setString(1, user.getUsername());
//            ps.setString(2, user.getPassword());
//            ps.setInt(3, user.getId());
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public List<User> getAllUsers() {
//        List<User> users = new ArrayList<>();
//        String sql = "SELECT * FROM users";
//        try (Connection conn = DbUtil.getConnection();
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery(sql)) {
//            while (rs.next()) {
//                User user = new User();
//                user.setId(rs.getInt("id"));
//                user.setUsername(rs.getString("username"));
//                user.setPassword(rs.getString("password"));
//                users.add(user);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return users;
//    }
//
//    public User getUserById(int userId) {
//        User user = null;
//        String sql = "SELECT * FROM users WHERE id=?";
//        try (Connection conn = DbUtil.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setInt(1, userId);
//            try (ResultSet rs = ps.executeQuery()) {
//                if (rs.next()) {
//                    user = new User();
//                    user.setId(rs.getInt("id"));
//                    user.setUsername(rs.getString("username"));
//                    user.setPassword(rs.getString("password"));
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return user;
//    }
//
//    public User getUserByUsername(String username) {
//        User user = null;
//        String sql = "SELECT * FROM users WHERE username=?";
//        try (Connection conn = DbUtil.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setString(1, username);
//            try (ResultSet rs = ps.executeQuery()) {
//                if (rs.next()) {
//                    user = new User();
//                    user.setId(rs.getInt("id"));
//                    user.setUsername(rs.getString("username"));
//                    user.setPassword(rs.getString("password"));
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return user;
//    }
//}




package com.example;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public void addUser(User user) {
        String sql = "INSERT INTO users(username, password) VALUES (?, ?)";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int userId) {
        String sql = "DELETE FROM users WHERE id=?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        String sql = "UPDATE users SET username=?, password=? WHERE id=?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = DbUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getUserById(int userId) {
        User user = null;
        String sql = "SELECT * FROM users WHERE id=?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User getUserByUsername(String username) {
        User user = null;
        String sql = "SELECT * FROM users WHERE username=?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
