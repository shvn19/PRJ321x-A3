package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDAO;
import model.Account;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void processRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");	//vietnamese
		
		try {
			// This part is write in previous assignment, delete it to test, recover it when in need
//			request.getSession(true)		// take out session from request, boolean true generate a new session if session not exist
//				.invalidate();		// invalidate the session vo hieu hoa, go bo het cac thuoc tinh co trong session
			
			//make sure that email is valid
			String regexMail = "^[A-Z0-9_a-z]+@[A-Z0-9\\.a-z]+\\.[A-Za-z]{2,6}$";
			String regex = "[a-zA-Z0-9_!@#$%^&*]+";
			
			//collect data from a login form
			String user = request.getParameter("username");
			String password = request.getParameter("password");
			Account acc = new Account();
			acc.setName(user); acc.setPwd(password);
			HttpSession session = request.getSession(true); //session's been invalidated so surely generate new session
			
			//Check mail and password are valid, if not assign attribute error and come back to login page to input again
			if (!password.matches(regex)||!user.matches(regexMail)) {	
				session.setAttribute("error", "invalid syntax");
				session.setAttribute("isNew", "no");
				response.sendRedirect(request.getContextPath()+"/signin.jsp");
				return;
			}
			
			AccountDAO accDao = new AccountDAO();
			if (accDao.checkAccountExists(user, password, acc)) {
				//set cookies
				if(request.getParameter("rememberme")!=null) {
					Cookie cookie1 = new Cookie("user",user);
					Cookie cookie2 = new Cookie("password",password);
					cookie1.setMaxAge(1800);
					cookie2.setMaxAge(1800);
					response.addCookie(cookie1);
					response.addCookie(cookie2);
				}	
				
				//set session
				session.setAttribute("account", acc);
				//login is valid, now redirect to index page of admin
				response.sendRedirect(request.getContextPath()+"/index.jsp");

			}
			else { //if user email and pass doesnot match than set error and come back login page
				session.setAttribute("error", "wrong username or password");
				session.setAttribute("isNew", "no"); //set isNew any value other than null to check if it's first time visit signin.jsp
				response.sendRedirect(request.getContextPath()+"/signin.jsp");
			}
		} catch (NullPointerException e) {		//if encounter error session is null then forward back the request to login page
			RequestDispatcher rd = request.getRequestDispatcher(request.getContextPath()+"/signin.jsp");
			rd.forward(request, response);
		} catch (Exception ex) {
			response.getWriter().println(ex);
		}
	}

}
