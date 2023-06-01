package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.CompleteDTO;
import model.CompleteLogic;

/**
 * Servlet implementation class PurchaseComplete
 */
@WebServlet("/CompleteController")
public class CompleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CompleteController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		 CompleteLogic completeLogic = new CompleteLogic();
		try {
			CompleteDTO completeDTO = completeLogic.execute(request,response);
			System.out.println("エラーコードは" + completeDTO.getErrorCode());
			request.setAttribute("completeDTO", completeDTO);
			if(completeDTO.getErrorCode() == 1) {
				PurchaseController purchaseController = new PurchaseController();
				System.out.println(request.getParameter("product_number"));
				System.out.println(request.getParameter("searchName"));
				System.out.println(request.getParameter("searchCategoryCode"));
				System.out.println(request.getParameter("searchLowPrice"));
				purchaseController.doPost(request, response);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/complete.jsp");
		rd.forward(request, response);
	}
}
