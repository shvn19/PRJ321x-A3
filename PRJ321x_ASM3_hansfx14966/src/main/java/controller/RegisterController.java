package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountDAO;
import model.Account;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String mail = request.getParameter("mail");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		
		AccountDAO dao = new AccountDAO();
		Account acc = new Account(mail,password,name,address,phone);
		
		//Check all fields are validated?
		String validationResult = acc.validate();
		if (!validationResult.isEmpty()) {
			request.setAttribute("mail", mail);
			request.setAttribute("password", password);
			request.setAttribute("name", name);
			request.setAttribute("address", address);
			request.setAttribute("phone", phone);
			request.setAttribute("error", validationResult);
			RequestDispatcher rd = request.getRequestDispatcher("registeraccount.jsp");
			rd.forward(request, response);
			return;
		}
		
		//if email existed come back register form
		if (dao.checkMailExists(mail)) {
			request.setAttribute("mail", mail);
			request.setAttribute("password", password);
			request.setAttribute("name", name);
			request.setAttribute("address", address);
			request.setAttribute("phone", phone);
			request.setAttribute("error", "Email is already existed");
			System.out.println((request.getContextPath()+"/registeraccount.jsp").substring(1));
			RequestDispatcher rd = request.getRequestDispatcher("registeraccount.jsp");
			rd.forward(request, response);
		} 
		else {
			//try to insert new account into database, if success, proceed to index.jsp
			if(dao.insertNewAccount(mail,password,name,address,phone)){
			response.sendRedirect(request.getContextPath()+"/index.jsp");
			} else { //if anything went wrong, come back to register page and fill in all fields
				request.setAttribute("mail", mail);
				request.setAttribute("password", password);
				request.setAttribute("name", name);
				request.setAttribute("address", address);
				request.setAttribute("phone", phone);
				request.setAttribute("error", "Something went wrong!");
				System.out.println((request.getContextPath()+"/registeraccount.jsp").substring(1));
				RequestDispatcher rd = request.getRequestDispatcher("registeraccount.jsp");
				rd.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
