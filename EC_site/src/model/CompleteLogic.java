package model;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import dao.PurchaseDAO;
import dao.StockDAO;
import dto.CompleteDTO;
import dto.Information;
import dto.Product;

public class CompleteLogic {
	public CompleteDTO execute(HttpServletRequest request,HttpServletResponse response) throws Exception {
	
		String entryNumber = request.getParameter("entryNumber");
		int	productNumber =  Integer.parseInt(request.getParameter("product_number"));
		String quantityParam = request.getParameter("quantity");
		int quantity = 1;
		int searchPageNumber = Integer.parseInt(request.getParameter("searchNowPage"));
		int errorCode = 0;
		String quantityErrorMessage = "";
		String entryNumberErrorMessage = "";
		ProductDAO dao = new ProductDAO();
		Product purchasedProduct = dao.selectProductByProductNumber(productNumber);
		PurchaseDAO purchaseDAO = new PurchaseDAO();
		String productImg = purchasedProduct.getProduct_img();
		String productName = purchasedProduct.getProductName();
		String searchProductName = request.getParameter("searchName");
		int searchCategoryCode = Integer.parseInt(request.getParameter("searchCategoryCode"));
		int searchLowPrice = 0;
		int searchUpPrice = 9999999;
	//	int searchLowPrice = Integer.parseInt(request.getParameter("lowPrice"));
	//	int searchUpPrice = Integer.parseInt(request.getParameter("upPrice"));
		int searchRecommendCode = Integer.parseInt(request.getParameter("searchRecommendCode"));
		
		try {
			if(entryNumber == null || entryNumber.isEmpty()) {
				System.out.println("申込番号が取得出来ませんでした");
				errorCode = 1;
			}else if(!entryNumber.matches("^\\d+$")) {
				System.out.println("申込番号が数値ではありません");
				errorCode = 1;
			}else if(entryNumber.length() != 18) {
				System.out.println("申込番号が18桁ではありません");
				errorCode = 1;
			}
			if(quantityParam == null || quantityParam.isEmpty()) {
				quantityErrorMessage = "1〜99までの数字を入力して下さい。";
				errorCode = 1;
			}else if(!quantityParam.matches("^\\d+$")) {
				quantityErrorMessage = "1〜99までの数字を入力して下さい。";
				errorCode = 1;
			}else{
				quantity = Integer.parseInt(quantityParam);
				if(quantity < 1 || quantity > 99) {
					quantityErrorMessage = "1〜99までの数字を入力して下さい。";
					errorCode = 1;
				}
			}
			if(purchaseDAO.selectEntryNumberByEntryNumber(entryNumber) != null) {
				entryNumberErrorMessage = "既に購入済みです。";
			}
			if(purchasedProduct != null) {
				StockDAO stockDAO = new StockDAO();
				String stock = stockDAO.selectStockByProductNumber(productNumber);
				if(quantity > Integer.parseInt(stock)) {
					quantityErrorMessage = "在庫がありません";
					errorCode = 1;
				}else {
					stockDAO.updateStockByProductNumber(quantity, productNumber);
				}
			}
			int purchasePrice = purchasedProduct.getProductPrice() * quantity;
			
			Information information = new Information(entryNumber, "00000000", productNumber, quantity, purchasedProduct.getProductPrice());

			purchaseDAO.insert(information);
			
			if(errorCode == 1) {
				CompleteDTO completeDTO = new CompleteDTO(null,null,entryNumber,searchProductName,searchCategoryCode,searchLowPrice,
						searchUpPrice,searchRecommendCode,searchPageNumber,errorCode,quantityErrorMessage,entryNumberErrorMessage,productNumber);
				return completeDTO;
			}else {
				CompleteDTO completeDTO = new CompleteDTO(productImg,productName,entryNumber,searchProductName,searchCategoryCode,searchLowPrice,
						searchUpPrice,searchRecommendCode,searchPageNumber,errorCode,quantityErrorMessage,entryNumberErrorMessage,productNumber);
				return completeDTO;
			}
		}catch(Exception e) {
			System.out.println(e);
			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			rd.forward(request, response);
		}
	return null;
	}
}