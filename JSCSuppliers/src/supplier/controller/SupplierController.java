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
import supplier.dao.UserDAO;
import supplier.model.Supplier;

/**
 * Servlet implementation class SupplierController
 */
@WebServlet("/SupplierController")
public class SupplierController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static SupplierDAO dao;  
	private static UserDAO sdao;
	private int sid;
	private String forward;
	private static String LIST = "staff/listSupplier.jsp";
	private static String UPDATE = "staff/updateSupplier.jsp";
	private static String VIEW = "staff/viewSupplier.jsp";		
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupplierController() {
        super();
        dao = new SupplierDAO();
        sdao = new UserDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		
		
		if(action.equalsIgnoreCase("listSupplier")) {
			forward = LIST;
	        request.setAttribute("users", UserDAO.getUserSupplier());        
		}		
		if(action.equalsIgnoreCase("viewSupplier")) {    
			forward = VIEW;
			sid = Integer.parseInt(request.getParameter("sid"));
			request.setAttribute("sup", dao.getSupplierById(sid));
		}		
		if(action.equalsIgnoreCase("updateSupplier")) { 
			forward = UPDATE;
			sid = Integer.parseInt(request.getParameter("sid"));
	        request.setAttribute("sup", dao.getSupplierById(sid));	        
		}
		if(action.equalsIgnoreCase("deleteSupplier")) {
			forward = LIST;
	        int id = Integer.parseInt(request.getParameter("id"));
		    sdao.deleteUser(id);		    
		    request.setAttribute("users", UserDAO.getUserSupplier());        
		}

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Supplier sup = new Supplier();
		sup.setName(request.getParameter("name"));
		sup.setAddress(request.getParameter("address"));
		sup.setCity(request.getParameter("city"));
		sup.setPostcode(Integer.parseInt(request.getParameter("postcode")));
		sup.setState(request.getParameter("state"));
		sup.setCountry(request.getParameter("country"));		
		sup.setPhoneno(request.getParameter("phoneno"));		

		String sid = request.getParameter("sid");
	    sup.setSid(Integer.parseInt(sid));
	    dao.updateSupplier(sup);
	   
	    request.setAttribute("users", UserDAO.getUserSupplier());
	    RequestDispatcher view = request.getRequestDispatcher("staff/listSupplier.jsp");
        view.forward(request, response);
	}

}
