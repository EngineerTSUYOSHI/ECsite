package model;

import java.util.List;

import dao.CategoryDAO;
import dao.ProductDAO;
import dto.Category;
import dto.Product;

public class Logic {
	CategoryDAO cdao = new CategoryDAO();
	ProductDAO dao = new ProductDAO();
	List<Category> categoryList;
	List<Category> recommendList;
	List<Product> list;
	
	//全てのDB情報を抜き出す
	public List<Product> findAll() {
		try {
			list = dao.findAll();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return null;
		}return list;
	}
	//カテゴリーのリストを抜き出す
	public List<Category> findCategory() {
		try {
			categoryList = cdao.findCategory();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return null;
		}return categoryList;
	}
	//おすすめ順のリストを抜き出す
	public List<Category> findRecommend() {
		try {
			recommendList = cdao.findRecommend();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return null;
		}return recommendList;
	}
}
