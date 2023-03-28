package model;

import java.util.ArrayList;

import dao.CategoryDAO;
import dao.ProductDAO;
import dto.Category;
import dto.Product;
import dto.ProductListDTO;
import dto.SearchDTO;

public class ProductListLogic {
	ArrayList<Product> products;
	ArrayList<Category> categorys;
	ArrayList<Category> recommends;
	
	public ProductListDTO execute() {
//		検索条件DTOの生成、値の初期値を設定
		SearchDTO search = new SearchDTO();
		search.setProductName(null);
		search.setCategoryCode(0);
		search.setLowPrice(0);
		search.setUpPrice(999999);
//		取得位置の初期値設定
//		ProductListDTO plDTO = new ProductListDTO(); 
//		plDTO.setLimit(15);
//		plDTO.setOffset(0);
//		plDTO.setCategory(0);
//		plDTO.setRecommend(1);
//		int limit = 15;
//		int offset = 0;
//		並び順設定
		Category category = new Category();
		
		String order_by;
		if(category.getCategory_code() == 1) {
			order_by = "recommend";
		}else if(category.getCategory_code() == 2) {
			order_by = "product_price";
		}else if(category.getCategory_code() == 2) {
			order_by = "product_price desc";
		}
		
		ProductDAO pDAO = new ProductDAO();
		CategoryDAO cDAO = new CategoryDAO();
		try {
//			商品情報の取得、Listへ格納
			products = pDAO.findAll();
//			商品カテゴリDTOを生成
			categorys = cDAO.findCategory();
//			並び順DTOを生成
			recommends = cDAO.findRecommend();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return null;
		}
//		商品画面DTO生成
		ProductListDTO plDTO = new ProductListDTO(products,categorys,recommends,search,15,0);
		return plDTO;
	}
}
