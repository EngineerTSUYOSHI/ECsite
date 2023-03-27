package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryDAO;
import dao.ProductDAO;
import dto.Category;
import dto.Product;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String productName = request.getParameter("product_name");
		int category_code = Integer.parseInt(request.getParameter("category_code"));
		int minPrice = Integer.parseInt(request.getParameter("minPrice"));
		int maxPrice = Integer.parseInt(request.getParameter("maxPrice"));
		int recommend = Integer.parseInt(request.getParameter("recommend"));
		
		System.out.println(recommend);
		
		System.out.println(productName + ' ' + category_code + ' ' + minPrice + ' ' + maxPrice);
		
		CategoryDAO cdao = new CategoryDAO();
		List<Category> categoryList;
		List<Category> recommendList;
		
		ProductDAO dao = new ProductDAO();
		List<Product> list;
		try {
			categoryList = cdao.findCategory();
			request.setAttribute("categoryList", categoryList);
			recommendList = cdao.findRecommend();
			request.setAttribute("recommendList", recommendList);
			list = dao.SearchOne(productName, category_code, minPrice, maxPrice);
			request.setAttribute("list", list);
			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			rd.forward(request, response);
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}
