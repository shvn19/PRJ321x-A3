package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ListProductDAO;
import model.Cart;
import model.CartDetails;
import model.Product;

/**
 * Servlet implementation class AddToCartController
 */
@WebServlet("/AddToCartController")
public class AddToCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCartController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");	//vietnamese
		try {
			HttpSession session = request.getSession(true);
			String idd = request.getParameter("id");
			String action = request.getParameter("action");
			if (action!=null && action.equalsIgnoreCase("add")) {
				if(session.getAttribute("cart")==null) {
					session.setAttribute("cart", new Cart());
				}
				int id = Integer.parseInt(idd);
				Product p =new ListProductDAO().getProduct(id);
				Cart c = (Cart) session.getAttribute("cart");
				int quantity = Integer.parseInt(request.getParameter("quantity"));
				c.add(new Product(p.getId(),p.getName(),p.getDescription(),p.getPrice(),p.getSrc(), p.getType(),p.getBrand()),quantity);
				System.out.println(""+id+"-"+quantity );
				response.sendRedirect(request.getContextPath()+"/cart.jsp");
			} 
			else if (action!=null && action.equalsIgnoreCase("delete")){
				int id = Integer.parseInt(idd);
				Cart c = (Cart) session.getAttribute("cart");
				c.remove(id);
				response.sendRedirect(request.getContextPath()+"/cart.jsp");
			} 
			else if (action!=null&& action.equalsIgnoreCase("test")) {
				System.out.println("In servlet addtocart");
				Cart c = (Cart) session.getAttribute("cart");
				c.remove(1);
				response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/"));
			} 
			else if (action!=null&& action.equalsIgnoreCase("savechanges")) {
				System.out.println("In servlet addtocart action savechangesffff");
				String arr = request.getParameter("table");
				System.out.println(arr);
				
				//Split the text into each product, split by ','
				String[] ps = arr.split(","); 
				
				Cart c = (Cart) session.getAttribute("cart");
				
				for (String p: ps) {
					String[] pe = p.split("/"); 
					c.saveChange(Integer.parseInt(pe[0]), Integer.parseInt(pe[1]));
				}
				
				List<CartDetails> cds = c.getItems();
				for (CartDetails cd:cds) {
					System.out.println(""+cd.getProduct().getName()+"-"+cd.getQuantity()+"-$"+cd.getProduct().getPrice()+"-$"+cd.getAmount());
				}
				//response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/"));
			}

			/* response.sendRedirect(request.getContextPath()+"/cart.jsp"); */
		} catch (Exception ex) {
			response.getWriter().println(ex);
		}
	}

}
