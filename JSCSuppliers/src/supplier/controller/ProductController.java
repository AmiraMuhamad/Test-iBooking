package supplier.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import supplier.dao.ProductDAO;
import supplier.dao.SupplierDAO;
import supplier.model.Product;

/**
 * Servlet implementation class ProductController
 */
@WebServlet("/ProductController")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO dao;
	private SupplierDAO sdao;  
	private int pid;
	private String forward;
	private static String LIST = "staff/listProduct.jsp";
	private static String VIEW = "staff/viewProduct.jsp";	
	private static String UPDATE = "staff/updateProduct.jsp";
	private static String ADD = "staff/addProduct.jsp";	
	private static String LISTALL = "staff/listAll.jsp";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductController() {
        super();
        dao = new ProductDAO();
        sdao = new SupplierDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
        
		if(action.equalsIgnoreCase("listProduct")) {
			forward = LIST;
	        request.setAttribute("prods", ProductDAO.getAllProduct());       
		}		
		if(action.equalsIgnoreCase("listAll")) {
			forward = LISTALL;
			request.setAttribute("prods", ProductDAO.getSupplierProducts());
		}
		if(action.equalsIgnoreCase("viewProduct")) { 
			forward = VIEW;
			pid = Integer.parseInt(request.getParameter("pid"));  
	        request.setAttribute("prod", dao.getProductById(pid));
		}
		if(action.equalsIgnoreCase("updateProduct")) {
			forward = UPDATE;
			pid = Integer.parseInt(request.getParameter("pid"));  
	        Product prod = dao.getProductById(pid);
	        request.setAttribute("prod", prod);    
	        request.setAttribute("sups", SupplierDAO.getSupplierId());
		}
		if(action.equalsIgnoreCase("deleteProduct")) {
			forward = LIST;
			pid = Integer.parseInt(request.getParameter("pid"));  
			dao.deleteProduct(pid);
			request.setAttribute("prods", dao.getAllProduct());    
		}		
		if(action.equalsIgnoreCase("addProduct")) {
			forward = ADD;
			request.setAttribute("sups", SupplierDAO.getSupplierId());
		}
         
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Product prod = new Product();
		prod.setProductname(request.getParameter("productname"));
		prod.setPrice(Double.parseDouble(request.getParameter("price")));
		prod.setQuantity(Integer.parseInt(request.getParameter("quantity")));
		prod.setSid(Integer.parseInt(request.getParameter("sid")));
		    
		String pid = request.getParameter("pid");
	    
	    if(pid ==null || pid.isEmpty()) {
	    	dao.add(prod);
		}
		else {
			prod.setPid(Integer.parseInt(pid));
			dao.updateProduct(prod);
		}
	    
	    request.setAttribute("prods", ProductDAO.getAllProduct());
	    RequestDispatcher view = request.getRequestDispatcher("staff/listProduct.jsp");
        view.forward(request, response);
	}

}
