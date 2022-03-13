package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ListProductDAO;
import dao.OrdersDAO;
import model.Cart;
import model.Orders;

/**
 * Servlet implementation class SearchController
 */
@WebServlet("/SearchController")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final int numberOfProductsPerPage = 10;

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			String action = (String) request.getAttribute("action");
			String action2 = request.getParameter("action");
			if (action == null)
				action = action2; // if action not come from POST then take it from GET method
			if (action.equals("search")) {
				String text = request.getParameter("searchString");
				request.setAttribute("searchString", text);

				String pageIndexString = (String) request.getParameter("pageIndex");

				if (pageIndexString == null) {
					pageIndexString = "1";
				}

				int pageIndex = Integer.parseInt(pageIndexString);

				ListProductDAO dao = new ListProductDAO();

				request.setAttribute("results", dao.pagingSearchedProducts(text, pageIndex, numberOfProductsPerPage));
				
				int totalNumberOfProducts = dao.getTotalNumberOfSearchedProducts(text);
				int numberOfPages;
				if (totalNumberOfProducts % numberOfProductsPerPage == 0)
					numberOfPages = totalNumberOfProducts / numberOfProductsPerPage;
				else
					numberOfPages = totalNumberOfProducts / numberOfProductsPerPage + 1;
				request.setAttribute("pageIndex", pageIndex);
				request.setAttribute("totalNumberOfProducts", totalNumberOfProducts);
				request.setAttribute("numberOfPages", numberOfPages);
				
				RequestDispatcher rd = request.getRequestDispatcher("search.jsp");
				rd.forward(request, response);
			} else if (action.equals("home")) {
				String pageIndexString = (String) request.getParameter("pageIndex");

				if (pageIndexString == null) {
					pageIndexString = "1";
				}

				int pageIndex = Integer.parseInt(pageIndexString);

				ListProductDAO dao = new ListProductDAO();

				request.setAttribute("results", dao.pagingSearchedProducts("", pageIndex, numberOfProductsPerPage));

				int totalNumberOfProducts = dao.getTotalNumberOfSearchedProducts("");
				int numberOfPages;
				if (totalNumberOfProducts % numberOfProductsPerPage == 0)
					numberOfPages = totalNumberOfProducts / numberOfProductsPerPage;
				else
					numberOfPages = totalNumberOfProducts / numberOfProductsPerPage + 1;
				request.setAttribute("pageIndex", pageIndex);
				request.setAttribute("totalNumberOfProducts", totalNumberOfProducts);
				request.setAttribute("numberOfPages", numberOfPages);
				RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
				rd.forward(request, response);
			}
		} catch (Exception ex) {
			response.getWriter().println(ex);
//			ex.printStackTrace();
		}

	}

}
