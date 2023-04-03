package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Category;

public class CategoryDAO {
	private final String JDBC_URL = "jdbc:mysql://localhost/test";
	private final String DB_USER = "root";
	private final String DB_PASS = "root";
	
	public ArrayList<Category> selectCategoryByCategoryType(int category_type) throws ClassNotFoundException {
		ArrayList<Category> categoryList = new ArrayList<>();
//		recommend = Search.getRecommend();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			String sql =
					"Select * from category where category_type = ? and delete_flg = 0 order by category_code";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, category_type);
			
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				int category_code = rs.getInt("category_code");
				String category_name = rs.getString("category_name");				
				categoryList.add(new Category(category_code, category_name));
			}
		}catch (SQLException e){
			e.printStackTrace();
			return null;
		}
		return categoryList;
	}
}
