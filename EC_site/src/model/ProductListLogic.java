package model;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryDAO;
import dao.ProductDAO;
import dto.Category;
import dto.OrderDTO;
import dto.Product;
import dto.ProductListDTO;
import dto.SearchDTO;

public class ProductListLogic {
	ArrayList<Product> products;
	ArrayList<Category> categorys;
	ArrayList<Category> recommends;
	
	public ProductListDTO execute(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
//		検索条件DTOの生成、値の初期値を設定
		SearchDTO search = new SearchDTO();
		if(request.getParameter("product_name") == null) {
			//nullの場合は空文字をセット
			search.setProductName("");
		}else {
			search.setProductName(request.getParameter("product_name"));
		}
		if(request.getParameter("category_code") == null) {
			search.setCategoryCode(0);
		}else {
			search.setCategoryCode(Integer.parseInt(request.getParameter("category_code")));
		}
		if(request.getParameter("lowPrice") == null) {
			search.setLowPrice(0);
		}else {
			try {
				search.setLowPrice(Integer.parseInt(request.getParameter("lowPrice")));
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("価格（下限）は数値ではありません");
				RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
				rd.forward(request, response);
			}
		}
		if(request.getParameter("upPrice") == null) {
			search.setUpPrice(9999999);
		}else {
			try {
				search.setUpPrice(Integer.parseInt(request.getParameter("upPrice")));
			}
			catch(Exception e){
				e.printStackTrace();
				System.out.println("価格（上限）は数値ではありません");
				RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
				rd.forward(request, response);
			}
		}
		if(request.getParameter("recommend") == null) {
			//初期値はおすすめ順
			search.setRecommendCode(1);
		}else {
			search.setRecommendCode(Integer.parseInt(request.getParameter("recommend")));
		}
		System.out.println(request.getParameter("page_num"));
		System.out.println("productName is " + search.getProductName());
		System.out.println("categoryCode is " + search.getCategoryCode());
		System.out.println("low is " + search.getLowPrice());
		System.out.println("up is " + search.getUpPrice());
		System.out.println("recommendCode is " + search.getRecommendCode());
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
//			3-1?
			categorys = cDAO.findCategory();
//			4-1?  4-3-1 並び順DTOを生成
			recommends = cDAO.findRecommend();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return null;
		}
//		4-3 並び順設定
		OrderDTO orderList = new OrderDTO(category.getCategory_code(),category.getCategory_name());
		
		
//		6-1 商品画面DTO生成
		ProductListDTO plDTO = new ProductListDTO(products,categorys,recommends,search,15,0);
//		,pageStart,orderCode,orderList
		return plDTO;
	}
}
