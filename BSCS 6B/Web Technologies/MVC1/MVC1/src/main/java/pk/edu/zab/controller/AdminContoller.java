package pk.edu.zab.controller;

import java.io.IOException;

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

			view="/WEB-INF/jsps/user.setting.jsp";
			if(session != null){
				request.getRequestDispatcher("/WEB-INF/jsps/admin.setting.jsp").forward(request, response);
			}
			else {
				request.getRequestDispatcher("/WEB-INF/jsps/admin.login.jsp").forward(request, response);
			}
		}



	}
