package model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import dao.StockDAO;
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
		int productNumber = Integer.parseInt(request.getParameter("product_number"));
		System.out.println("押された商品番号は：" + productNumber);
//		選択された商品番号から、商品情報を取得
		Product purchasedProduct = dao.selectProductByProductNumber(productNumber);
		System.out.println("name is " + purchasedProduct.getProductName());
		PurchaseDTO PurchasedDTO = new PurchaseDTO(purchasedProduct);
		
//		PurchaseDAO purchaseDAO = new PurchaseDAO();
//		String purchaseNumber = purchaseDAO.selectEntryNumberByUserId();
//		System.out.println("申込番号は" + purchaseNumber);
		int quantity;
		if(request.getParameter("quantity") == null) {
			quantity = 0;
		}else {
			quantity = Integer.parseInt(request.getParameter("quantity"));
		}
		
		if(!(quantity >= 1 && quantity <= 99)) {
			System.out.println("数量が範囲外です");
		}
//		在庫数を取り出す
		StockDAO stockDAO = new StockDAO();
		int stock = stockDAO.selectStockByProductNumber(productNumber);
		System.out.println("在庫数は" + stock);
//		在庫数より発注数が多い場合
		if(stock < quantity) {
			System.out.println("在庫が足りません");
		}else {
//		在庫が足りる場合は在庫から発注分を引く
			stockDAO.minusStockByProductNumber(quantity,productNumber);
		}
		return PurchasedDTO;
	}
}
