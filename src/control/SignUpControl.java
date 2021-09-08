package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import context.DBContext;
import dao.AccountDAO;
import entity.Account;

/**
 * Servlet implementation class SignUpControl
 */
@WebServlet(name = "SignUpControl", urlPatterns= {"/signup"})
public class SignUpControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException, ClassNotFoundException, SQLException {
	    	response.setContentType("text/html;character=UTF-8");
			String username = request.getParameter("user");
			String password = request.getParameter("pass");

				AccountDAO dao = new AccountDAO(DBContext.getConnection());
				Account a = dao.checkAccountExist(username);
				if(a == null) {
					//được signup
					dao.signUp(username, password);
					response.sendRedirect("Login.jsp");
				}else {
					request.setAttribute("mess", "Username already exists, please choose another username");
					request.getRequestDispatcher("SignUp.jsp").forward(request, response);
				}
			}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
