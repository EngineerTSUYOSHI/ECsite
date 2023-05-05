package model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import dao.PurchaseDAO;
import dto.Product;
import dto.PurchaseDTO;

public class PurchaseLogic {
	public  PurchaseDTO execute(HttpServletRequest request,HttpServletResponse response) throws Exception {
//		数字が入力されたかをチェック
//		if(!request.getParameter("quantity").matches("^[0-9]+$")) {
//			System.out.println("数量は数値ではありません");
//			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
//			rd.forward(request, response);
//		}
//		商品情報を取得
		ProductDAO dao = new ProductDAO();
		request.getAttribute("dto");
		int productNo = Integer.parseInt(request.getParameter("product_number"));
		System.out.println("押された商品番号は：" + productNo);
//		選択された商品番号から、商品情報を取得
		Product purchasedProduct = dao.selectProductByProductNumber(productNo);
		
		PurchaseDTO PurchasedDTO = new PurchaseDTO(purchasedProduct);
		
		PurchaseDAO purchaseDAO = new PurchaseDAO();
		String purchaseNumber = purchaseDAO.selectEntryNumberByUserId();
		System.out.println("申込番号は" + purchaseNumber);
		return PurchasedDTO;
	}
}
