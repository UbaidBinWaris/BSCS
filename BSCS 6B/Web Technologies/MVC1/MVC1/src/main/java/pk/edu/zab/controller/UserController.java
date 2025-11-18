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

		if (kase.equals("auth")) {

			// session = request.getSession();
			if (session != null) {
				if (!session.getAttribute("UserName").toString().equals(null)) {
					request.getRequestDispatcher("/user/welcome").forward(request, response);
				}
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
		else if (kase.equals("login")) {
			System.out.println("Inside Login Case");
			if (session != null) {
				request.getRequestDispatcher("/WEB-INF/jsps/user.welcome.jsp").forward(request, response);
			}

			else {
				request.getRequestDispatcher("/WEB-INF/jsps/user.login.jsp").forward(request, response);
			}

		}
		else if(kase.equals("setting")){
			view="/WEB-INF/jsps/user.setting.jsp";
			if(session != null){
				request.getRequestDispatcher("/WEB-INF/jsps/user.setting.jsp").forward(request, response);
			}
			else {
				request.getRequestDispatcher("/WEB-INF/jsps/user.login.jsp").forward(request, response);
			}
		}

		else if (kase.equals("logout")) {
			view = "/WEB-INF/jsps/user.login.jsp";
			session.invalidate();
			session = null;
			response.sendRedirect("login");

		} else {
			System.out.println("not of the Usercontroller cases are fired");
		}
		System.out.println("User Controller End");

	}

}
