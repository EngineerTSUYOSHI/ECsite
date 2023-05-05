package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PurchaseDAO {
	private final String JDBC_URL = "jdbc:mysql://localhost/test";
	private final String DB_USER = "root";
	private final String DB_PASS = "root";
	
	public String selectEntryNumberByUserId() throws Exception {
		String entry_number = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			String sql =
					"Select max(entry_number) from purchase_tbl where user_id = '00000000' and delete_flg = 0";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			ResultSet rs = pStmt.executeQuery();
			System.out.println("rs is " + rs);
			if(rs == null) {
				LocalDateTime nowDate = LocalDateTime.now();
				DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("yyyyMMdd");
                String formatNowDate = dtf3.format(nowDate);
                System.out.println(formatNowDate); 
				entry_number = formatNowDate + "00000000" + 00;
			}
			while(rs.next()) {
				entry_number = rs.getString("max(entry_number)");
			}
		}catch (SQLException e){
			e.printStackTrace();
			return null;
		}
		System.out.println("申込番号は" + entry_number);
		return entry_number;
	}
}
