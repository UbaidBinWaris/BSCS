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

public class AdminContoller extends HttpServlet {
	private RequestDispatcher dispatch;
	private String view, kase;
	// private String controller;
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

		if (kase == null) {
			System.out.println("kase is null in AdminContoller");
			request.getRequestDispatcher("/WEB-INF/jsps/error.jsp").forward(request, response);
			return;
		}

		session = request.getSession(false);

		if ("login".equals(kase)) {
			System.out.println("Inside Admin Login Case");
			if (session != null && session.getAttribute("AdminName") != null) {
				// Already logged in, fetch users and show settings page
				UserDAO userDAO = new UserDAO();
				List<UserModel> userList = userDAO.getAllUsers();
				request.setAttribute("userList", userList);
				request.getRequestDispatcher("/WEB-INF/jsps/admin.setting.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/WEB-INF/jsps/admin.login.jsp").forward(request, response);
			}
		}
		else if ("adminLogin".equals(kase)) {
			System.out.println("Inside Admin Login Validation Case");

			// Get username and password from request
			username = request.getParameter("username");
			password = request.getParameter("password");

			// Hardcoded admin credentials validation
			if ("admin".equals(username) && "admin123".equals(password)) {
				// Login successful - create session
				session = request.getSession(true);
				session.setAttribute("AdminName", username);
				System.out.println("Admin login successful for: " + username);

				// Fetch all users from database
				UserDAO userDAO = new UserDAO();
				List<UserModel> userList = userDAO.getAllUsers();
				request.setAttribute("userList", userList);

				// Forward to admin settings page with user list
				request.getRequestDispatcher("/WEB-INF/jsps/admin.setting.jsp").forward(request, response);
			} else {
				// Login failed - show error message
				System.out.println("Admin login failed for: " + username);
				request.setAttribute("errorMessage", "Invalid username or password. Please try again.");
				request.getRequestDispatcher("/WEB-INF/jsps/admin.login.jsp").forward(request, response);
			}
		}
		else if ("setting".equals(kase)) {
			view = "/WEB-INF/jsps/admin.setting.jsp";
			if (session != null && session.getAttribute("AdminName") != null) {
				// Fetch all users and display
				UserDAO userDAO = new UserDAO();
				List<UserModel> userList = userDAO.getAllUsers();
				request.setAttribute("userList", userList);
				request.getRequestDispatcher("/WEB-INF/jsps/admin.setting.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/WEB-INF/jsps/admin.login.jsp").forward(request, response);
			}
		}
		else if ("logout".equals(kase)) {
			if (session != null) {
				session.invalidate();
			}
			response.sendRedirect("login");
		}
		else {
			System.out.println("Unknown case in AdminContoller: " + kase);
			request.getRequestDispatcher("/WEB-INF/jsps/admin.login.jsp").forward(request, response);
		}

		System.out.println("Admin Controller End");
	}
}
