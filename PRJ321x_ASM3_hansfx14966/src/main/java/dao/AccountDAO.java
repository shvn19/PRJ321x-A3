package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import context.DBContext;
import model.Account;

public class AccountDAO {
	
	public boolean checkAccountExists(String usermail,String password, Account account) throws Exception{
		DBContext context = new DBContext();
		Connection conn = context.getConnection();
		
		String sql = "select * from account where user_mail=? and password=?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, usermail);
		stmt.setString(2, password);
		
		ResultSet rs=stmt.executeQuery();
		
		if(rs.next()) {
			account.setUsr(usermail);
			account.setPwd(password);
			account.setRole(rs.getInt(3));
			account.setName(rs.getNString(4));
			account.setAddress(rs.getNString(5));
			account.setPhone(rs.getString(6));
			return true;
		}
		
		return false;
	}
	
	public AccountDAO() {}
	
	public boolean checkMailExists(String usermail) {
		DBContext context = new DBContext();
		Connection conn=null;
		try {
			conn = context.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Connection failed CheckMailExists");
		}
		
		String sql = "select * from account where user_mail=?";
		
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, usermail);
			
			ResultSet rs=stmt.executeQuery();
			
			if(rs.next()) {
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Something wrong with statement CheckMailExists");
		}
		
		return false;
	}

	public boolean insertNewAccount(String mail, String password, String name, String address, String phone) {
		// TODO Auto-generated method stub
		DBContext context = new DBContext();
		Connection conn=null;
		try {
			conn = context.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Connection failed insertNewAccount");
			return false;
		}
		
		String sql = "insert into account values (?,?,?,?,?,?)";
		
		PreparedStatement stmt=null;
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, mail);
			stmt.setString(2, password);
			stmt.setInt(3, 2);
			stmt.setNString(4, name);
			stmt.setNString(5, address);
			stmt.setString(6, phone);
			
			int affectedRows = stmt.executeUpdate();
			
			if (affectedRows == 0) {
				System.out.println("Creating order failed");
				stmt.close();
				conn.close();
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Something wrong with statement insertNewAccount");
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return false;
			}
			
			return false;
		}
		
		return true;
	}
}
