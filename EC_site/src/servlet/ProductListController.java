package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ProductListDTO;
import model.ProductListLogic;

/**
 * Servlet implementation class ProductListController
 */
@WebServlet("/ProductListController")
public class ProductListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		ProductListLogic pll = new ProductListLogic();
		ProductListDTO list = pll.execute();
		request.setAttribute("list", list);
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
//		検索条件を取得
		String productName = request.getParameter("product_name");
		int category_code = Integer.parseInt(request.getParameter("category_code"));
		int lowPrice = Integer.parseInt(request.getParameter("lowminPrice"));
		int upPrice = Integer.parseInt(request.getParameter("upPrice"));
		int recommend = Integer.parseInt(request.getParameter("recommend"));		
		System.out.println("カテゴリ:" + recommend);
		System.out.println("商品名:" + productName + " カテゴリ: " + category_code + " 最低価格: " + lowPrice + " 最高価格: " + upPrice);		
		
		ProductListLogic pll = new ProductListLogic();
		ProductListDTO list = pll.execute();
		request.setAttribute("list", list);
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		rd.forward(request, response);
	}
}
