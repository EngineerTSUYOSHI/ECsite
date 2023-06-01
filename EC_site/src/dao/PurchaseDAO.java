package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Information;

public class PurchaseDAO {
	private final String JDBC_URL = "jdbc:mysql://localhost/EC_site";
	private final String DB_USER = "root";
	private final String DB_PASS = "root";
	
	public String selectEntryNumberByUserId() throws Exception {
		String entry_number = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			String sql =
					"Select max(entry_number) from purchase_tbl where user_id = '00000000' and date(create_datetime) = CURDATE()";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			ResultSet rs = pStmt.executeQuery();

			while(rs.next()) {
				entry_number = rs.getString("max(entry_number)");
			}
		}catch (SQLException e){
			e.printStackTrace();
			return null;
		}
		return entry_number;
	}
	
	public void insert(Information information) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");	
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			String sql =
				"insert into purchase_tbl (entry_number , user_id, product_number, quantity, delete_flg) \n"
				+ "values(?,?,?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, information.getEntryNumber());
			pStmt.setString(2, information.getUserId());
			pStmt.setInt(3, information.getProductNumber());
			pStmt.setInt(4, information.getQuantity());
			pStmt.setInt(5, 0);
			int result = pStmt.executeUpdate();
			if(result != 1) {
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String selectEntryNumberByEntryNumber(String entryNumber) throws Exception {
		String entry_number = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			String sql =
					"select entry_number from purchase_tbl where entry_number = ? and delete_flg = 0";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, entryNumber);
			ResultSet rs = pStmt.executeQuery();

			while(rs.next()) {
				entry_number = rs.getString("max(entry_number)");
			}
		}catch (SQLException e){
			e.printStackTrace();
			return null;
		}
		return entry_number;
	}
}
