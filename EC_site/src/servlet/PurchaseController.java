package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.PurchaseDTO;
import model.PurchaseLogic;

/**
 * Servlet implementation class PurchaseController
 */
@WebServlet("/PurchaseController")
public class PurchaseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PurchaseController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");		
		PurchaseLogic purchaseLogic = new PurchaseLogic();
		try {
			PurchaseDTO dto = purchaseLogic.execute(request,response);
			request.setAttribute("dto", dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/purchasedProduct.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");		
		PurchaseLogic purchaseLogic = new PurchaseLogic();
		try {
			PurchaseDTO purchaseDTO = purchaseLogic.execute(request,response);
			request.setAttribute("purchaseDTO", purchaseDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/purchasedProduct.jsp");
		rd.forward(request, response);
	}
}
