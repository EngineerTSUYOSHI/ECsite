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
	final int LIMIT = 15;
	public ProductListDTO execute(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Product> products;
		ArrayList<Category> categorys;
		ArrayList<Category> recommends;
//		パラメータの値を変数に代入
		String productName = request.getParameter("product_name");
		String category_code = request.getParameter("category_code");
		String inputLowPrice = request.getParameter("lowPrice");
		String inputUpPrice = request.getParameter("upPrice");
		
//		検索条件DTOの生成、値の初期値を設定
		SearchDTO search = new SearchDTO();
//		errorFlg > 1でエラーページへ
		int errorFlg = 0;
		if(productName == null) {
//		nullの場合は空文字をセット
			search.setProductName("");
		}else {
			search.setProductName(productName);
		}
//		カテゴリーコードのチェック
		if(category_code == null) {
			search.setCategoryCode(0);
		}else if(!category_code.matches("^[0-9]+$") ){
			errorFlg = 1;
		}else {
			search.setCategoryCode(Integer.parseInt(category_code));
		}
//		価格（下限）チェック
//		JSP側で入力値を参照する為の変数を用意
		String lowPrice = "";
		if(inputLowPrice == null || inputLowPrice.isEmpty() ) {
			search.setLowPrice(0);
		}else if(!inputLowPrice.matches("^[0-9]+$")){
			errorFlg = 1;
			System.out.println("価格（下限）は数値ではありません");
		}else {
			search.setLowPrice(Integer.parseInt(inputLowPrice));
			lowPrice = inputLowPrice;
		}
		request.setAttribute("lowPrice", lowPrice);
//		価格（上限）チェック
		String upPrice = "";
		if(inputUpPrice == null || inputUpPrice.isEmpty()) {
			search.setUpPrice(9999999);
		}else if(!inputUpPrice.matches("^[0-9]+$")){
			errorFlg = 1;
			System.out.println("価格（上限）は数値ではありません");
		}else if(Integer.parseInt(inputUpPrice) > 9999999) {
			errorFlg = 1;
			System.out.println("価格（上限）は上限を超えています");
		}else {
			search.setUpPrice(Integer.parseInt(inputUpPrice));
			upPrice = inputUpPrice;
		}
		request.setAttribute("upPrice", upPrice);
//		おすすめ順のチェック
		if(request.getParameter("recommend") == null) {
			//初期値はおすすめ順
			search.setRecommendCode(1);
		}else if(request.getParameter("recommend").isEmpty()) {
			errorFlg = 1;
			System.out.println("並び順コードが空です");
		}else if(!request.getParameter("recommend").matches("^[0-9]+$")){
			errorFlg = 1;
			System.out.println("並び順コードが数値ではありません");
		}else {
			search.setRecommendCode(Integer.parseInt(request.getParameter("recommend")));
		}
//		ページ番号から取得開始位置を設定
		int now_page = 1;
		int offset = 0;
		
		if((request.getParameter("now_page"))== null || request.getParameter("now_page").isEmpty()){
			System.out.println("ページ番号がnullです");
		}else if(!request.getParameter("now_page").matches("^[0-9]+$")){
			errorFlg = 1;
			System.out.println("ページ番号が数値ではありません");
		}else {
			now_page = Integer.parseInt(request.getParameter("now_page"));
			offset = (now_page * LIMIT) - LIMIT;
		}
		search.setLimit(LIMIT);
		search.setOffset(offset);
//		errorFlg ＝ 1でエラーページへ
		if(errorFlg == 1) {
			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			rd.forward(request, response);
		}
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
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
//		並び順設定
		OrderDTO orderList = new OrderDTO(category.getCategoryCode(),category.getCategoryName());		
//		商品画面DTO生成
		ProductListDTO productListDTO = new ProductListDTO(products,categorys,recommends,search,now_page);
		return productListDTO;
	}
}
