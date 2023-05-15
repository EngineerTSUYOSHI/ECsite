package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import dao.PurchaseDAO;
import dao.StockDAO;
import dto.Product;
import dto.PurchaseDTO;
import dto.SearchDTO;

public class PurchaseLogic {
	public  PurchaseDTO execute(HttpServletRequest request,HttpServletResponse response) throws Exception {
//		数字が入力されたかをチェック
//		if(!request.getParameter("quantity").matches("^[0-9]+$")) {
//			System.out.println("数量は数値ではありません");
//			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
//			rd.forward(request, response);
//		}
		
//		商品情報を取得
		try {
		String errorMessage = "";
//		1-2-2商品DTO設定
		ProductDAO dao = new ProductDAO();
		request.getAttribute("dto");
		int productNumber = Integer.parseInt(request.getParameter("product_number"));
//		選択された商品番号から、商品情報を取得
		Product purchasedProduct = dao.selectProductByProductNumber(productNumber);
//		商品情報を取得できなければエラーメッセージを表示して終了処理
		if(purchasedProduct == null) {
			errorMessage = "商品はありません";
		}
		Product productDTO = new Product
				(purchasedProduct.getProductNumber(), purchasedProduct.getProductName(), 
						purchasedProduct.getProductPrice(), purchasedProduct.getProduct_img());

//		2、数量チェック
		int quantity;
		if(request.getParameter("quantity") == null) {
			quantity = 1;
		}else {
			quantity = Integer.parseInt(request.getParameter("quantity"));
		}
		if(!(quantity >= 1 && quantity <= 99)) {
			System.out.println("数量エラーメッセージ：1～99までの数値を入力してください。");
		}
//		3、申込番号作成
		PurchaseDAO purchaseDAO = new PurchaseDAO();
		String  applicationNumber = purchaseDAO.selectEntryNumberByUserId();
		if(applicationNumber == null) {
			applicationNumber = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))
					+ "00000000" + "00";
		}else {
			int setNumber = Integer.parseInt(applicationNumber.substring(16)) + 1;
			applicationNumber = applicationNumber.substring(0,16) + String.format("%02d", setNumber);
		}
		
//		4,在庫数を取り出す
		StockDAO stockDAO = new StockDAO();
		
		String stock = stockDAO.selectStockByProductNumber(productNumber);
		if(stock == null || stock.isEmpty()) {
			stock = "0";
		}
		String quantityErrorMessage = "";
		if(Integer.parseInt(stock) < quantity) {
			quantityErrorMessage = "在庫数がありません";
		}
//		小計計算
		int totalPrice = purchasedProduct.getProductPrice() * quantity;
		
		SearchDTO searchDTO = new SearchDTO();
		String productName = purchasedProduct.getProductName();
		int searchCategoryCode = searchDTO.getCategoryCode();
		int searchLowPrice = searchDTO.getLowPrice();
		int searchUpPrice = searchDTO.getUpPrice();
		int orderCode = searchDTO.getRecommendCode();
		Product product = productDTO;
		
		PurchaseDTO PurchasedDTO = new PurchaseDTO(productName, searchCategoryCode, searchLowPrice, searchUpPrice, orderCode,
				product, errorMessage, applicationNumber, quantity, quantityErrorMessage, totalPrice);
		return PurchasedDTO;
		
		}catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}
}
