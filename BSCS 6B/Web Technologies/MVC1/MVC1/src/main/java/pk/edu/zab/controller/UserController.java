package pk.edu.zab.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pk.edu.zab.dao.UserDAO;
import pk.edu.zab.model.UserModel;

@SuppressWarnings("serial")
public class UserController extends HttpServlet {
	private RequestDispatcher dispatch;
	private String view, kase;
	private String username;
	private String password;
	UserModel um = new UserModel();
	private int userId;
	private String UserName;
	private String getRoles;
	HttpSession session = null;
	private int role_id;


    protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		kase = (String) request.getAttribute("kase");

		if ("auth".equals(kase)) {

			HttpSession session = request.getSession(false);
			if (session != null && session.getAttribute("UserName") != null) {
				request.getRequestDispatcher("/user/welcome").forward(request, response);
			} else {
				username = request.getParameter("username");
				password = request.getParameter("password");
				System.out.println("ID pass: " + username);
				// need to check user & pass
				UserDAO ud = new UserDAO();
				UserModel um = ud.checkUser(username, password);

				if (um.getUser() != null) {
					System.out.println("Valid user");
					userId = (int) um.getIduser();
					UserName = (String) um.getUser();
					System.out.println("userId: " + userId);

					session = request.getSession();
					session.setAttribute("UserName", UserName);

					request.setAttribute("session", session);
					request.getRequestDispatcher("WEB-INF/jsps/user.welcome.jsp").forward(request, response);
				} else {
					System.out.println("Not valid user");
				}
			}
		}
		// edit this portion
		else if ("login".equals(kase)) {
			System.out.println("Inside Login Case");
			HttpSession session = request.getSession(false);
			if (session != null && session.getAttribute("UserName") != null) {
				request.getRequestDispatcher("/WEB-INF/jsps/user.welcome.jsp").forward(request, response);
			}

			else {
				request.getRequestDispatcher("/WEB-INF/jsps/user.login.jsp").forward(request, response);
			}

		}
		else if ("signup".equals(kase)) {
			System.out.println("Inside Signup Case");
			request.getRequestDispatcher("/WEB-INF/jsps/user.signup.jsp").forward(request, response);
		}
		else if ("register".equals(kase)) {
			System.out.println("Inside Register Case - Processing Signup");
			
			// Get form parameters
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String fname = request.getParameter("fname");
			
			// Validation
			StringBuilder errorMessages = new StringBuilder();
			
			// Validate username
			if (username == null || username.trim().isEmpty()) {
				errorMessages.append("Username is required. ");
			} else if (username.length() < 3 || username.length() > 50) {
				errorMessages.append("Username must be between 3 and 50 characters. ");
			} else if (!username.matches("^[a-zA-Z0-9_]+$")) {
				errorMessages.append("Username can only contain letters, numbers, and underscores. ");
			}
			
			// Validate password
			if (password == null || password.trim().isEmpty()) {
				errorMessages.append("Password is required. ");
			} else if (password.length() < 6 || password.length() > 100) {
				errorMessages.append("Password must be between 6 and 100 characters. ");
			}
			
			// Validate email
			if (email == null || email.trim().isEmpty()) {
				errorMessages.append("Email is required. ");
			} else if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
				errorMessages.append("Invalid email format. ");
			} else if (email.length() > 100) {
				errorMessages.append("Email is too long. ");
			}
			
			// Validate full name
			if (fname == null || fname.trim().isEmpty()) {
				errorMessages.append("Full name is required. ");
			} else if (fname.length() < 2 || fname.length() > 100) {
				errorMessages.append("Full name must be between 2 and 100 characters. ");
			} else if (!fname.matches("^[a-zA-Z\\s]+$")) {
				errorMessages.append("Full name can only contain letters and spaces. ");
			}
			
			// If validation fails, redirect back with error message
			if (errorMessages.length() > 0) {
				System.out.println("Validation errors: " + errorMessages.toString());
				request.setAttribute("error", errorMessages.toString());
				request.setAttribute("username", username);
				request.setAttribute("email", email);
				request.setAttribute("fname", fname);
				request.getRequestDispatcher("/WEB-INF/jsps/user.signup.jsp").forward(request, response);
				return;
			}
			
			// Sanitize inputs (trim whitespace)
			username = username.trim();
			email = email.trim();
			fname = fname.trim();
			
			// Create new user model
			UserModel newUser = new UserModel();
			newUser.setUser(username);
			newUser.setPass(password);
			newUser.setEmail(email);
			newUser.setFname(fname);
			
			// Save to database
			UserDAO ud = new UserDAO();
			try {
				// Check duplicates first
				String exists = ud.existsUsernameOrEmail(username, email);
				if ("username".equals(exists)) {
					request.setAttribute("error", "Registration failed. Username already exists.");
					request.setAttribute("username", username);
					request.setAttribute("email", email);
					request.setAttribute("fname", fname);
					request.getRequestDispatcher("/WEB-INF/jsps/user.signup.jsp").forward(request, response);
					return;
				} else if ("email".equals(exists)) {
					request.setAttribute("error", "Registration failed. Email already registered.");
					request.setAttribute("username", username);
					request.setAttribute("email", email);
					request.setAttribute("fname", fname);
					request.getRequestDispatcher("/WEB-INF/jsps/user.signup.jsp").forward(request, response);
					return;
				} else if ("exists".equals(exists)) {
					request.setAttribute("error", "Registration failed. User or email already exists.");
					request.setAttribute("username", username);
					request.setAttribute("email", email);
					request.setAttribute("fname", fname);
					request.getRequestDispatcher("/WEB-INF/jsps/user.signup.jsp").forward(request, response);
					return;
				}
				// proceed to insert
				ud.addUser(newUser);
				System.out.println("User registered successfully: " + username);
				
				// Set success message and redirect to login
				request.setAttribute("message", "Registration successful! Please login.");
				request.getRequestDispatcher("/WEB-INF/jsps/user.login.jsp").forward(request, response);
			} catch (Exception e) {
				System.out.println("Error registering user: " + e.getMessage());
				request.setAttribute("error", "Registration failed. Please try again later.");
				request.setAttribute("username", username);
				request.setAttribute("email", email);
				request.setAttribute("fname", fname);
				request.getRequestDispatcher("/WEB-INF/jsps/user.signup.jsp").forward(request, response);
			}
		}
		else if("setting".equals(kase)) {
			view="/WEB-INF/jsps/user.setting.jsp";
			HttpSession session = request.getSession(false);
			if(session != null && session.getAttribute("UserName") != null){
				request.getRequestDispatcher("/WEB-INF/jsps/user.setting.jsp").forward(request, response);
			}
			else {
				request.getRequestDispatcher("/WEB-INF/jsps/user.login.jsp").forward(request, response);
			}
		}
		else if("welcome".equals(kase)) {
			HttpSession session = request.getSession(false);
			if(session != null && session.getAttribute("UserName") != null){
				request.getRequestDispatcher("/WEB-INF/jsps/user.welcome.jsp").forward(request, response);
			}
			else {
				request.getRequestDispatcher("/WEB-INF/jsps/user.login.jsp").forward(request, response);
			}
		}

		else if ("logout".equals(kase)) {
				view = "/WEB-INF/jsps/user.login.jsp";
				HttpSession session = request.getSession(false);
				if (session != null) {
					session.invalidate();
				}
				response.sendRedirect("login");

			} else {
				System.out.println("not of the Usercontroller cases are fired");
				System.out.println("not of the Usercontroller cases are fired");
			}
			System.out.println("User Controller End");

		}

}
