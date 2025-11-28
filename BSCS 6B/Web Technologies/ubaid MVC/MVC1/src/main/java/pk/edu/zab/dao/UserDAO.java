package pk.edu.zab.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
				String query = 	"insert into user(user, pass, email, fname) values( '"+user.getUser()+"' , '"+user.getPass()+"' , '"+user.getEmail()+"' , '"+user.getFname()+"' )";
		
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
