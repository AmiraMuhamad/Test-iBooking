package supplier.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import supplier.dao.ProductDAO;
import supplier.dao.UserDAO;
import supplier.model.User;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginLogoutController")
public class LoginLogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO dao;
	HttpSession session;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginLogoutController() {
		super();
		dao = new UserDAO();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			//get the current session
			HttpSession session = request.getSession(true);
			//set current session to null.
			session.setAttribute("sessionEmail", null);
			//destroy session
			session.invalidate();
			//redirect to login page
			response.sendRedirect("login.jsp");
		}catch (Throwable ex) {
			System.out.println(ex);
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {

			User user = new User();
			//retrieve and set email and password
			user.setEmail(request.getParameter("email"));
			user.setPassword(request.getParameter("password"));

			user = UserDAO.login(user);
			//set user session if user is valid
			if(user.isValid()){
				session = request.getSession(true);
				session.setAttribute("sessionId", user.getId());
				System.out.println(user.getId());
				session.setAttribute("sessionEmail", user.getEmail());  //set current session based on email
				session.setAttribute("sessionRole", user.getRole()); 
				
				if(user.getRole().equals("staff")) {
					request.setAttribute("user", UserDAO.getUserByEmail(user.getEmail()));   					
					request.setAttribute("prods", ProductDAO.getSupplierProducts());
					RequestDispatcher view = request.getRequestDispatcher("staff/listAll.jsp"); // staff page
					view.forward(request, response);	
				}
				else {
					request.setAttribute("user", UserDAO.getUserByEmail(user.getEmail())); 				
					request.setAttribute("us", UserDAO.getUserById(user.getId()));
					RequestDispatcher view = request.getRequestDispatcher("supplier/home.jsp"); 	 // supplier page
					view.forward(request, response);	
				}
				
							
			}
			//redirect to invalidLoggin.jsp if user is not valid
			else{
				response.sendRedirect("invalidLogin.jsp");
			}		
		}

		catch (Throwable ex) {
			System.out.println(ex);
		}
	}

}
