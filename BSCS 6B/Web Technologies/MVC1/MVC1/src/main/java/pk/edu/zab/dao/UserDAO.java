package pk.edu.zab.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;

import pk.edu.zab.model.UserModel;
import pk.edu.zab.util.DbUtil;





public class UserDAO {
	private Connection con;
	
	public UserDAO(){
		con = DbUtil.getConnection();
	}
	Statement stmt;
	ResultSet rs;
	RequestDispatcher dis;
	UserModel um = new UserModel();
	
	public UserModel checkUser(String user, String pass){
		boolean check = false;
		try {
			// Use PreparedStatement to prevent SQL injection
			String query = "SELECT * FROM users WHERE (user = ? OR user_name = ?) AND (pass = ?) LIMIT 1";
			java.sql.PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, user);
			pstmt.setString(2, user);
			pstmt.setString(3, pass);
			rs = pstmt.executeQuery();

			if(rs.next()){
				// Prefer username, fallback to user_name
				String uname = null;
				try { uname = rs.getString("user"); } catch (Exception e) { }
				if (uname == null || uname.isEmpty()) {
					try { uname = rs.getString("user_name"); } catch (Exception e) { uname = null; }
				}
				if (uname == null) uname = "";
				um.setUser(uname);
				// set other fields if available
				try { um.setIduser(rs.getInt("iduser")); } catch (Exception e) { try { um.setIduser(rs.getInt("id")); } catch (Exception ex) { um.setIduser(0); }}
				try { um.setEmail(rs.getString("email")); } catch (Exception e) { um.setEmail(""); }
				System.out.println("inside checkuser_Method: "+ um.getUser());
				check = true;
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return um;
	}
	
	// Check if username or email already exists. Returns a message: null if ok, otherwise reason.
	public String existsUsernameOrEmail(String username, String email) {
		try {
			String query = "SELECT user_name, email FROM users WHERE user_name = ? OR email = ? LIMIT 1";
			java.sql.PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, username);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String foundUser = null;
				try { foundUser = rs.getString("user_name"); } catch (Exception e) {}
				String foundEmail = null;
				try { foundEmail = rs.getString("email"); } catch (Exception e) {}
				pstmt.close();
				if (foundUser != null && !foundUser.isEmpty() && foundUser.equalsIgnoreCase(username)) {
					return "username";
				}
				if (foundEmail != null && !foundEmail.isEmpty() && foundEmail.equalsIgnoreCase(email)) {
					return "email";
				}
				return "exists";
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void addUser(UserModel user) {
		try {
			// Use PreparedStatement to prevent SQL injection
			String query = "INSERT INTO users(user_name, pass, email, fname) VALUES(?, ?, ?, ?)";
			java.sql.PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, user.getUser());
			pstmt.setString(2, user.getPass());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getFname());

			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error adding user: " + e.getMessage());
		}
	}

	public List<UserModel> getAllUsers() {
		List<UserModel> userList = new ArrayList<UserModel>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM users");

			while(rs.next()) {
				UserModel user = new UserModel();
				// Try to get iduser or id column
				try {
					user.setIduser(rs.getInt("iduser"));
				} catch (SQLException e) {
					try {
						user.setIduser(rs.getInt("id"));
					} catch (SQLException e2) {
						user.setIduser(0);
					}
				}

				// Get user/username column
				try {
					user.setUser(rs.getString("user"));
				} catch (SQLException e) {
					try {
						user.setUser(rs.getString("user_name"));
					} catch (SQLException e2) {
						user.setUser("");
					}
				}

				// Get password column
				try {
					user.setPass(rs.getString("pass"));
				} catch (SQLException e) {
					try {
						user.setPass(rs.getString("password"));
					} catch (SQLException e2) {
						user.setPass("");
					}
				}

				// Get email column
				try {
					user.setEmail(rs.getString("email"));
				} catch (SQLException e) {
					user.setEmail("");
				}

				// Get fname/first_name column
				try {
					user.setFname(rs.getString("fname"));
				} catch (SQLException e) {
					try {
						user.setFname(rs.getString("first_name"));
					} catch (SQLException e2) {
						user.setFname("");
					}
				}

				userList.add(user);
				System.out.println("User added: " + user.getUser() + " - " + user.getEmail());
			}
			System.out.println("Total users fetched: " + userList.size());
		} catch (SQLException e) {
			System.out.println("Error fetching users: " + e.getMessage());
			e.printStackTrace();
		}
		return userList;
	}
}
