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
	private final String JDBC_URL = "jdbc:mysql://localhost/EC_site";
	private final String DB_USER = "root";
	private final String DB_PASS = "root";
	
	public ArrayList<Product> selectProductBySearch(SearchDTO search) throws Exception {
		ArrayList<Product> list = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			int exsistProductNameFlg = 0;
			int exsistcategoryCodeFlg = 0;
			String sql = "select product_number, product_name, category_code, product_price, recommend, valid_start_date"
					+ ", valid_end_date, delete_flg, delete_flg, create_datetime, update_datetime, product_img"
					+ " from products where delete_flg = 0 and date_format(sysdate(), '%y/%m/%d') between valid_start_date and valid_end_date ";
//			価格の検索範囲を設定
			sql += "and product_price >= ? and product_price <= ? ";
//			商品名が空でなければ検索
			if(!search.getProductName().isEmpty()) {
				sql += " and product_name=? ";
				exsistProductNameFlg += 1;
			}
//			カテゴリが選ばれたら検索条件に追加
			if(search.getCategoryCode() !=0) {
				sql += " and category_code = ? ";
				exsistcategoryCodeFlg += 1;
			}
//			並び順タグに応じて条件を設定
			if(search.getRecommendCode() == 1) {
				sql += "order by recommend ";
			}else if(search.getRecommendCode() == 2) {
				sql += "order by product_price, recommend ";
			}else if(search.getRecommendCode() == 3) {
				sql += "order by product_price desc, recommend ";
			}		
//			ページ番号に応じた範囲の商品を取得
			sql += "limit ? offset ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
//			sql += and product_price between ? and ? and product_name = ? and category_code = ? order by  recommend limit ? offset ?の場合
			if(exsistProductNameFlg == 1 & exsistcategoryCodeFlg ==1){
				pStmt.setInt(1, search.getLowPrice());
				pStmt.setInt(2, search.getUpPrice());
			    pStmt.setString(3, search.getProductName());
				pStmt.setInt(4, search.getCategoryCode());
				pStmt.setInt(5, search.getLimit());
				pStmt.setInt(6, search.getOffset());
//			sql += and product_price between ? and ? and product_name = ? order by  recommend limit ? offset ?の場合
			}else if(exsistProductNameFlg == 1 & exsistcategoryCodeFlg ==0){
				pStmt.setInt(1, search.getLowPrice());
				pStmt.setInt(2, search.getUpPrice());
			    pStmt.setString(3, search.getProductName());
				pStmt.setInt(4, search.getLimit());
				pStmt.setInt(5, search.getOffset());
//			sql += and product_price between ? and ? and category_code = ? order by  recommend limit ? offset ?の場合
			}else if(exsistProductNameFlg == 0 & exsistcategoryCodeFlg ==1){
				pStmt.setInt(1, search.getLowPrice());
				pStmt.setInt(2, search.getUpPrice());
				pStmt.setInt(3, search.getCategoryCode());
				pStmt.setInt(4, search.getLimit());
				pStmt.setInt(5, search.getOffset());
//			sql += and product_price between ? and ? order by  recommend limit ? offset ?の場合
			}else{
				pStmt.setInt(1, search.getLowPrice());
				pStmt.setInt(2, search.getUpPrice());
				pStmt.setInt(3, search.getLimit());
				pStmt.setInt(4, search.getOffset());
			}
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				int productNumber = rs.getInt("product_number");
				String productName = rs.getString("product_name");
				int categoryCode =rs.getInt("category_code");
				int productPrice = rs.getInt("product_price");
				int recommend = rs.getInt("recommend");
				String validateStartDate = rs.getString("valid_start_date");
				String validateEndDate = rs.getString("valid_end_date");
				int deleteFlg = rs.getInt("delete_flg");
				String createDatetime = rs.getString("create_datetime");
				String updateDatetime = rs.getString("update_datetime");
				String productImg = rs.getString("product_img");
				
				list.add(new Product(productNumber, productName, categoryCode, productPrice, recommend,
						validateStartDate, validateEndDate, deleteFlg, createDatetime, updateDatetime,productImg));
			}			
		}catch (SQLException e){
			e.printStackTrace();
			return null;
		}
		return list;
	}
//	選択された商品を商品番号から1件抜き出すメソッド
	public Product selectProductByProductNumber(int productNo) throws Exception {
		Product purchasedProduct = new Product();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			String sql = "select product_number, product_name, product_price, product_img from products where delete_flg = 0 "
					+ "and date_format(sysdate(), '%y/%m/%d') between valid_start_date and valid_end_date and product_number = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, productNo);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				int productNumber = rs.getInt("product_number");
				String productName = rs.getString("product_name");
				int productPrice = rs.getInt("product_price");
				String productImg = rs.getString("product_img");
				purchasedProduct = new Product(productNumber, productName, productPrice, productImg);
			}
		}catch (SQLException e){
			e.printStackTrace();
			return null;
		}
		return purchasedProduct;
	}	
	//	商品登録用のメソッド
	public void insertOne(Product product) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");	
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
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
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}	
}
