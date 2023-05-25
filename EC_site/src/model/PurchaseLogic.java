package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import dao.PurchaseDAO;
import dao.StockDAO;
import dto.Product;
import dto.PurchaseDTO;

public class PurchaseLogic {
	String  applicationNumber = "";
	int quantity = 1;
	String quantityErrorMessage = "";
	int totalPrice = 0;
	Product productDTO = null;
	String errorMessage = "";
	String stock = null;
	
	public  PurchaseDTO execute(HttpServletRequest request,HttpServletResponse response) throws Exception {
		/**
		 * 購入処理を実施
		 * @param request, response
		 * @return PurchaseDTO
		 * @throws Exception 
		 */
//		選択された商品番号から、商品情報を取得
		ProductDAO dao = new ProductDAO();
		int productNumber = Integer.parseInt(request.getParameter("product_number"));
		Product purchasedProduct = dao.selectProductByProductNumber(productNumber);
		
		try {
//			商品情報を取得できなければエラーメッセージを代入し処理を終える
			if(purchasedProduct == null) {
				errorMessage = "商品はありません";
//			賞品情報が合った場合	
			}else {
				productDTO = new Product
						(purchasedProduct.getProductNumber(), purchasedProduct.getProductName(), 
								purchasedProduct.getProductPrice(), purchasedProduct.getProduct_img());
//				数量チェック
				quantity = getQuantity(request);
				if(!(quantity >= 1 && quantity <= 99)) {
					quantityErrorMessage = "数量エラーメッセージ：1～99までの数値を入力してください。";
				}else {
//					申込番号作成
					applicationNumber = makeApplicationNumber(productNumber);
//					在庫数を取り出す
					stock = getStock(productNumber);
					if(Integer.parseInt(stock) < quantity) {
						quantityErrorMessage = "在庫数がありません";
					}
//					小計計算
					totalPrice = purchasedProduct.getProductPrice() * quantity;
				}
//			商品情報があった場合のelse句終わり
			}
//		    一覧ページの検索条件を取得
		    String searchProductname = request.getParameter("searchName");
		    int searchCategoryCode = Integer.parseInt(request.getParameter("searchCategoryCode"));
		    String searchLowPrice = request.getParameter("searchLowPrice");
		    String searchUpPrice = request.getParameter("searchUpPrice");
		    int orderCode = Integer.parseInt(request.getParameter("searchRecommendCode"));
		    int nowPage = Integer.parseInt(request.getParameter("searchNowPage"));
	
		    PurchaseDTO purchasedDTO = new PurchaseDTO(searchProductname, searchCategoryCode, searchLowPrice, searchUpPrice, orderCode,
					productDTO, errorMessage, applicationNumber, quantity, quantityErrorMessage, totalPrice, nowPage);
			return purchasedDTO;
		}catch(Exception e) {
			System.out.println(e);
			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			rd.forward(request, response);
		}
		return null;
	}
	
	/**
	 * リクエストから数量を取得
	 * @param request
	 * @return　数量
	 */
	private int getQuantity(HttpServletRequest request) {
		if(request.getParameter("quantity") != null) {
			quantity = Integer.parseInt(request.getParameter("quantity"));
		}
		return quantity;
	}
	
	/**
	 * 申込番号を生成
	 * @param productNumber
	 * @return　生成された申込番号
	 * @throws Exception
	 */
	private String makeApplicationNumber(int productNumber) throws Exception {
		PurchaseDAO purchaseDAO = new PurchaseDAO();
		applicationNumber = purchaseDAO.selectEntryNumberByUserId();
		if(applicationNumber == null) {
			applicationNumber = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))
					+ "00000000" + "00";
		}else {
			int setNumber = Integer.parseInt(applicationNumber.substring(16)) + 1;
			applicationNumber = applicationNumber.substring(0,16) + String.format("%02d", setNumber);
		}
		return applicationNumber;
	}
	
	/**
	 * 商品の在庫数を取得
	 * @param productNumber　商品番号
	 * @return　在庫数
	 * @throws Exception
	 */
	private String getStock(int productNumber) throws Exception {
		StockDAO stockDAO = new StockDAO();
		stock = stockDAO.selectStockByProductNumber(productNumber);
		if(stock == null || stock.isEmpty()) {
			stock = "0";
		}
		return stock;
	}
}
