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
//		errorFlg > 0でエラーページへ
		int errorFlg = 0;
		if(request.getParameter("product_name") == null) {
//		nullの場合は空文字をセット
			search.setProductName("");
		}else {
			search.setProductName(request.getParameter("product_name"));
		}
//		カテゴリーコードのチェック
		if(request.getParameter("category_code") == null) {
			search.setCategoryCode(0);
		}else if(!request.getParameter("category_code").matches("^[0-9]+$") ){
			errorFlg += 1;
		}else {
			search.setCategoryCode(Integer.parseInt(request.getParameter("category_code")));
		}
//		価格（下限）チェック
		if(request.getParameter("lowPrice") == null || request.getParameter("lowPrice").isEmpty()) {
			search.setLowPrice(0);
		}else if(!request.getParameter("lowPrice").matches("^[0-9]+$")){
			errorFlg += 1;
			System.out.println("価格（下限）は数値ではありません");
		}else {
			search.setLowPrice(Integer.parseInt(request.getParameter("lowPrice")));
		}
//		価格（上限）チェック
		if(request.getParameter("upPrice") == null || request.getParameter("upPrice").isEmpty()) {
			search.setUpPrice(9999999);
		}else if(!request.getParameter("upPrice").matches("^[0-9]+$")){
			errorFlg += 1;
			System.out.println("価格（上限）は数値ではありません");
		}else {
			search.setUpPrice(Integer.parseInt(request.getParameter("upPrice")));
		}
//		おすすめ順のチェック
		if(request.getParameter("recommend") == null) {
			//初期値はおすすめ順
			search.setRecommendCode(0);
		}else if(!request.getParameter("recommend").matches("^[0-9]+$")){
			errorFlg += 1;
			System.out.println("並び順コードが数値ではありません");
		}else {
			search.setRecommendCode(Integer.parseInt(request.getParameter("recommend")));
		}
//		ページ番号から取得開始位置を設定
		int now_page = 1;
		int limit = 15;
		int offset = 0;
		if((request.getParameter("now_page"))== null){
			System.out.println("ページ番号がnullです");
		}else if(!request.getParameter("now_page").matches("^[0-9]+$")){
			errorFlg += 1;
			System.out.println("ページ番号が数値ではありません");
		}else {
			now_page = Integer.parseInt(request.getParameter("now_page"));
			offset = (now_page * limit) - limit;
		}
		search.setLimit(limit);
		search.setOffset(offset);
//		errorFlg > 0でエラーページへ
		if(errorFlg > 0) {
			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			rd.forward(request, response);
		}
		
		System.out.println("Page No: " + now_page);
		System.out.println("productName is " + search.getProductName());
		System.out.println("categoryCode is " + search.getCategoryCode());
		System.out.println("low is " + search.getLowPrice());
		System.out.println("up is " + search.getUpPrice());
		System.out.println("recommendCode is " + search.getRecommendCode());
		System.out.println("limit is " + search.getLimit());
		System.out.println("offset is " + search.getOffset());
		System.out.println("--------------------------------");
		
		Category category = new Category();
		ProductDAO pDAO = new ProductDAO();
		CategoryDAO cDAO = new CategoryDAO();
		try {
//			商品情報の取得、Listへ格納
			products = pDAO.selectProductBySearch(search);
//			商品カテゴリDTOを生成
			categorys = cDAO.selectCategoryByCategoryType(1);
//			並び順DTOを生成
			recommends = cDAO.selectCategoryByCategoryType(2);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
//		4-3 並び順設定
		OrderDTO orderList = new OrderDTO(category.getCategory_code(),category.getCategory_name());		
//		6-1 商品画面DTO生成
		ProductListDTO productListDTO = new ProductListDTO(products,categorys,recommends,search,now_page);
		return productListDTO;
	}
}
