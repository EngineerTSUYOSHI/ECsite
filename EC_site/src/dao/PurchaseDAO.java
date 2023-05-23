package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
