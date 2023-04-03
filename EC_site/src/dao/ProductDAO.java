package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Product;
import dto.SearchDTO;

public class ProductDAO {
	
	private final String JDBC_URL = "jdbc:mysql://localhost/test";
	private final String DB_USER = "root";
	private final String DB_PASS = "root";
	
	public ArrayList<Product> selectProductBySearch(SearchDTO search) throws ClassNotFoundException {
		ArrayList<Product> list = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			String sql = "select * from products where delete_flg = 0 and sysdate() between valid_start_date and valid_end_date";
//			価格の検索範囲を設定
			sql += " and product_price between " + search.getLowPrice() + " and " + search.getUpPrice();
//			商品名が空でなければ検索
			if(!search.getProductName().isEmpty()) {
					sql += " and product_name='" + search.getProductName() + "'";
			}
//			カテゴリが選ばれたら検索条件に追加
			if(search.getCategoryCode() !=0) {
				sql += " and category_code=" + search.getCategoryCode();
			}
//			並び順タグに応じて条件を設定
			if(search.getRecommendCode() == 0) {
				sql += " order by recommend";
			}else if(search.getRecommendCode() == 1) {
				sql += " order by product_price";
			}else if(search.getRecommendCode() == 2) {
				sql += " order by product_price desc";
			}		
//			ページ番号に応じた範囲の商品を取得
			sql += " limit " + search.getLimit() + " offset " + search.getOffset();
			System.out.println("SQL文:" + sql);
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				int productNumber = rs.getInt("product_number");
				String productName = rs.getString("product_name");
				int category_code =rs.getInt("category_code");
				int productPrice = rs.getInt("product_price");
				int recommend = rs.getInt("recommend");
				String validateStartDate = rs.getString("valid_start_date");
				String validateEndDate = rs.getString("valid_end_date");
				int deleteFlg = rs.getInt("delete_flg");
				String createDatetime = rs.getString("create_datetime");
				String updateDatetime = rs.getString("update_datetime");
				String product_img = rs.getString("product_img");

				list.add(new Product(productNumber, productName, category_code, productPrice, recommend,
						validateStartDate, validateEndDate, deleteFlg, createDatetime, updateDatetime,product_img));
			}
		}catch (SQLException e){
			e.printStackTrace();
			return null;
		}
		return list;
	}
	
	public void insertOne(Product product) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");	
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			String product_name = product.getProductName();
			
			String sql =
					"insert into products (product_number ,product_name ,category_code ,product_price ,recommend ,valid_start_date ,valid_end_date ,delete_flg,product_img) \n"
					+ "values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, product.getProductNumber());
			pStmt.setString(2, product.getProductName());
			pStmt.setInt(3, product.getCategory_code());
			pStmt.setInt(4, product.getProductPrice());
			pStmt.setInt(5, product.getRecommend());
			pStmt.setString(6, product.getValidateStartDate());
			pStmt.setString(7, product.getValidateEndDate());
			pStmt.setInt(8, product.getDeleteFlg());
			pStmt.setString(9, product.getProduct_img());	
			int result = pStmt.executeUpdate();
			if(result != 1) {				
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Product> SearchOne(String name , int code, int minPrice, int maxPrice) throws ClassNotFoundException {
		ArrayList<Product> list = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			String sql =
					"Select * from products "
					+ "where product_name = ? or recommend = ? and product_price between ? and ?"  ;
//			order by 検索条件 limit 5 offset 0　でできる
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, name);
			pStmt.setInt(2, code);
			pStmt.setInt(3, minPrice);
			pStmt.setInt(4, maxPrice);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				int productNumber = rs.getInt("product_number");
				String productName = rs.getString("product_name");
				int category_code =rs.getInt("category_code");
				int productPrice = rs.getInt("product_price");
				int recommend = rs.getInt("recommend");
				String validateStartDate = rs.getString("valid_start_date");
				String validateEndDate = rs.getString("valid_end_date");
				int deleteFlg = rs.getInt("delete_flg");
				String createDatetime = rs.getString("create_datetime");
				String updateDatetime = rs.getString("update_datetime");
				String product_img = rs.getString("product_img");
				
				list.add(new Product(productNumber, productName, category_code, productPrice, recommend,
						validateStartDate, validateEndDate, deleteFlg, createDatetime, updateDatetime,product_img));
			}
		}catch (SQLException e){
			e.printStackTrace();
			return null;
		}
		return list;
	}
	
}
