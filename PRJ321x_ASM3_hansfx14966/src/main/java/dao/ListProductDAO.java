package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import model.Product;

public class ListProductDAO {

	//return the list of products by product name
	public List<Product> search(String characters) throws Exception {
		DBContext context = new DBContext();
		Connection conn = context.getConnection();
		
		String sql = "select * from products where product_name like ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		System.out.println(characters);
		stmt.setNString(1, "%"+characters+"%");
		
		ResultSet rs = stmt.executeQuery();
		
		List<Product> lp = new ArrayList();
		
		while(rs.next()) {
			Product p = new Product();
			p.setId(rs.getInt(1));
			p.setName(rs.getNString(2));
			p.setDescription(rs.getNString(3));
			p.setPrice(rs.getFloat(4));
			p.setSrc(rs.getString(5));
			p.setType(rs.getString(6));
			p.setBrand(rs.getString(7));
			
			lp.add(p);
		}
		
		stmt.close();
		conn.close();
		
		return lp;
	}
	
	//get the product
	public Product getProduct(int product_id) throws Exception {
		DBContext context = new DBContext();
		Connection conn = context.getConnection();
		
		String sql = "select * from products where product_id=?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setInt(1, product_id);
		
		ResultSet rs = stmt.executeQuery();
		
		Product p=new Product();
		if (rs.next()) {
			p.setId(rs.getInt(1));
			p.setName(rs.getNString(2));
			p.setDescription(rs.getNString(3));
			p.setPrice(rs.getFloat(4));
			p.setSrc(rs.getString(5));
			p.setType(rs.getString(6));
			p.setBrand(rs.getString(7));
		}
		
		return p;
	}
	
	public int getTotalNumberOfSearchedProducts (String searchString) {
		DBContext context = new DBContext();
		Connection conn = null;
		try {
			conn = context.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Can't connect");
		}
		
		String sql = "select count(*) from products where product_name like ?";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setNString(1, "%"+searchString+"%");
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				int total = rs.getInt(1);
				stmt.close();
				conn.close();
				return total;
			} else {
				stmt.close();
				conn.close();
				return 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Something wrong with statements 1");
		}
		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Can't close connection");
		}
		return 0;
	}
	
	public List<Product> pagingSearchedProducts (String searchString, int page, int numberShown) {
		
		Connection conn = null;
		
		try {
			conn = new DBContext().getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Can't connect");
		}
		
		String sql = "select * from products where product_name like ? order by product_id limit ? offset ?";
//		String sql = "select * from products where product_name like ? order by product_id offset ? rows fetch next ? rows only";
		
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setNString(1, "%"+searchString+"%");
			stmt.setInt(3, (page-1)*numberShown);
			stmt.setInt(2, numberShown);
			
			
//			stmt.setInt(2, (page-1)*numberShown);
//			stmt.setInt(3, numberShown);
			
			ResultSet rs = stmt.executeQuery();
			
			List<Product> lp = new ArrayList();
			
			while(rs.next()) {
				Product p = new Product();
				p.setId(rs.getInt(1));
				p.setName(rs.getNString(2));
				p.setDescription(rs.getNString(3));
				p.setPrice(rs.getFloat(4));
				p.setSrc(rs.getString(5));
				p.setType(rs.getString(6));
				p.setBrand(rs.getString(7));
				
				lp.add(p);
			}
			
			stmt.close();
			conn.close();
			
			return lp;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Something wrong with statements 2");
		}
		
		return null;
	}
	
	
}
