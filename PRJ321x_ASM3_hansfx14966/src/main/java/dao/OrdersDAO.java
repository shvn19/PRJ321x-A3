package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import model.Account;
import model.Cart;
import model.CartDetails;
import model.Orders;
import model.Product;
import model.ProductOrders;

public class OrdersDAO {

	// insert info of Order to data source, that including list of
	// products in cart (c) and info of buyer in Orders o
	public void insertOrder(Orders o, Cart c) throws Exception {
		DBContext context = new DBContext();
		Connection conn = context.getConnection();

		String sql = "insert into orders (user_mail,order_status,order_date,order_discount_code,order_address) "
				+ "values (?,?,?,?,?)";

		PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		stmt.setString(1, o.getUserMail());
		stmt.setInt(2, o.getStatus());
		stmt.setDate(3, o.getOrderDate());
		stmt.setString(4, o.getDiscount());
		stmt.setNString(5, o.getAddress());

		int affectedRows = stmt.executeUpdate();

		if (affectedRows == 0) {
			System.out.println("Creating order failed");
			throw new SQLException("Creating order failed, no rows affected.");
		}

		int order_id;

		try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				order_id = generatedKeys.getInt(1);
			} else {
				System.out.println("Creating order failed -- in generatedkeys");
				throw new SQLException("Creating user failed, no ID obtained.");
			}
		}

		System.out.println("Order ID = " + order_id);

		stmt.close();

		List<CartDetails> items = c.getItems();

		String sql_od = "insert into orders_detail values (?,?,?,?)";

		PreparedStatement st = conn.prepareStatement(sql_od);
		for (CartDetails cd : items) {

			st.setInt(1, order_id);
			st.setInt(2, cd.getProduct().getId());
			st.setInt(3, cd.getQuantity());
			st.setInt(4, (int) cd.getProduct().getPrice());

			int affectedRows_od = st.executeUpdate();

			if (affectedRows_od == 0) {
				System.out.println("Creating orders_detail failed -- " + cd.getProduct().getName());
				throw new SQLException("Creating user failed, no rows affected.");
			}

			System.out.println("Add product -- " + cd.getProduct().getName() + " into order");
		}
		st.close();
		conn.close();
	}

	public List<Orders> getListOrders(Account acc) {

		String user = acc.getUsr();

		DBContext context = new DBContext();
		Connection conn = null;
		try {
			conn = context.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Connection failed - getListOrders");
			return null;
		}

		String sql = "select * from orders where user_mail=?";

		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, user);

			ResultSet rs = stmt.executeQuery();

			List<Orders> lo = new ArrayList();

			while (rs.next()) {
				Orders o = new Orders();

				o.setOrderID(rs.getInt(2));
				o.setStatus(rs.getInt(3));
				o.setOrderDate(rs.getDate(4));
				o.setDiscount(rs.getString(5));
				o.setAddress(rs.getNString(6));
				
				//collect the list of ProductOrders
				List<ProductOrders> lpo = getListProductOrders(o.getOrderID());
				o.setLp(getListProductOrders(o.getOrderID()));
				
				//collect the total value of this order ==> setPrice, the name is so stupid
				float totalAmount = 0;
				ListProductDAO dao = new ListProductDAO();
				for (ProductOrders po:lpo) {
					try {
						Product p = dao.getProduct(po.getProductID());
						totalAmount+= p.getPrice()*po.getAmountProduct();
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Can't get Product -- getListOrders");
					}
				}
				o.setPrice(totalAmount);
				
				lo.add(o);
			}

			stmt.close();
			conn.close();

			return lo;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Something wrong with statement - getListOrders");
			return null;
		}

	}

	public List<ProductOrders> getListProductOrders(int order_id) {

		DBContext context = new DBContext();
		Connection conn = null;
		try {
			conn = context.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Connection failed - getListProductOrders");
			return null;
		}

		String sql = "select * from orders_detail where order_id=?";

		PreparedStatement stmt;
		
		try {
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, order_id);

			ResultSet rs = stmt.executeQuery();

			List<ProductOrders> lpo = new ArrayList();

			while (rs.next()) {
				ProductOrders po = new ProductOrders();
			
				po.setProductID(rs.getInt(2));
				po.setAmountProduct(rs.getInt(3));
				
				Product p=null;
				try {
					p = new ListProductDAO().getProduct(po.getProductID());
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Something wrong with getProduct - getListProductOrders");
				}
				po.setNameProduct(p.getName());


				lpo.add(po);
			}

			stmt.close();
			conn.close();

			return lpo;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Something wrong with statement - getListProductOrders");
			return null;
		}

	}

}
