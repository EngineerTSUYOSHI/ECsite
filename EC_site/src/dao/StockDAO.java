package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StockDAO {
	private final String JDBC_URL = "jdbc:mysql://localhost/test";
	private final String DB_USER = "root";
	private final String DB_PASS = "root";
//	在庫数を取り出す
	public int selectStockByProductNumber(int productNumber) throws Exception {
		int stock = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			String sql =
					"Select stock from stock where delete_flg = 0 and product_number = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);	
			pStmt.setInt(1, productNumber);
			ResultSet rs = pStmt.executeQuery();

			while(rs.next()) {
				stock = rs.getInt("stock");
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println("在庫数は" + stock);
		return stock;
	}
//	在庫から発注分を引く
	public void minusStockByProductNumber(int quantity, int productNumber) throws Exception {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			String sql =
					" Update stock set stock = stock - ? where product_number = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);	
			pStmt.setInt(1, quantity);
			pStmt.setInt(2, productNumber);
			int result = pStmt.executeUpdate();
			if(result != 1) {
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
