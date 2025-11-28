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
			stmt = con.createStatement();
			rs = stmt.executeQuery(" select * from users where user_name= '"+user+"' and pass= '"+pass+"' ");
			if(rs.next()){
				um.setUser(rs.getString("email"));
				System.out.println("inside checkuser_Method: "+ rs.getString("email"));
				check = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return um;
	}
	
	
	
	
	public void addUser(UserModel user) {
		try {
			//UserModel user = new UserModel();
			stmt = con.createStatement();
				String query = 	"insert into users(user, pass, email, fname) values( '"+user.getUser()+"' , '"+user.getPass()+"' , '"+user.getEmail()+"' , '"+user.getFname()+"' )";
		
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
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
