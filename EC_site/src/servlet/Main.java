package servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.ProductDAO;
import dto.Product;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
@MultipartConfig
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		商品登録を行う
		request.setCharacterEncoding("UTF-8");
		int productNumber = Integer.parseInt(request.getParameter("product_number"));
		String productName = request.getParameter("product_name");
		int category_code = Integer.parseInt(request.getParameter("category_code"));
		int productPrice = Integer.parseInt(request.getParameter("product_price"));
		int recommend = Integer.parseInt(request.getParameter("recommend"));
		String validateStartDate = request.getParameter("valid_start_date");
		String validateEndDate = request.getParameter("valid_end_date");
		int deleteFlg = Integer.parseInt(request.getParameter("delete_flg"));		
		Part part = request.getPart("product_img");
		String product_img = part.getSubmittedFileName();
		String path = getServletContext().getRealPath("/upload");
		part.write(path+File.separator+product_img);
		
		ProductDAO dao=new ProductDAO();
		dao.insertOne(new Product(productNumber,productName, category_code, productPrice, recommend
			, validateStartDate, validateEndDate , deleteFlg, product_img));
		doGet(request,response);
	}
}
