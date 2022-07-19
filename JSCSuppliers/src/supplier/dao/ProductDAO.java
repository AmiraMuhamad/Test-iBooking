package supplier.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import supplier.connection.ConnectionManager;
import supplier.model.Product;

public class ProductDAO {

	static Connection con = null;
	static ResultSet rs = null; 
	static PreparedStatement ps=null;
	static Statement stmt=null;
	String productname;
	double price;
	int pid, quantity, sid; 

	//add Supplier
	public void add(Product bean){		
		productname = bean.getProductname();
		price = bean.getPrice();
		quantity = bean.getQuantity();
		sid = bean.getSid();

		try {
			//call getConnection() method //3. create statement //4. execute query
			con = ConnectionManager.getConnection();
			//3. create statement
			ps=con.prepareStatement("insert into product(productname,price,quantity,sid)values(?,?,?,?)");
			ps.setString(1,productname);
			ps.setDouble(2,price);
			ps.setInt(3,quantity);
			ps.setInt(4,sid);
			//4. execute query
			ps.executeUpdate();
			//5. close connection
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		
		}
	}

	//list product

	public static List<Product> getAllProduct() { 
		List<Product> prods = new ArrayList<Product>(); 
		try { 
			//call getConnection() method
			con = ConnectionManager.getConnection();
			//3. create statement
			stmt = con.createStatement(); 
			//4. execute query
			rs = stmt.executeQuery("select * from Product order by pid");

			while (rs.next()) { 
				Product prod = new Product();
				prod.setPid(rs.getInt("pid"));	  
				prod.setProductname(rs.getString("productname"));
				prod.setPrice(rs.getDouble("price"));
				prod.setQuantity(rs.getInt("quantity"));
				prod.setSid(rs.getInt("sid"));
				prods.add(prod);

			} 
			//5. close connection
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return prods; 
	}


	//get product by Id
	public static Product getProductById(int pid) {
		Product prod = new Product();
		try {
			//call getConnection() method
			con = ConnectionManager.getConnection();
			//3. create statement
			ps=con.prepareStatement("select * from product where pid=?");
			ps.setInt(1, pid);
			//4. execute query
			rs = ps.executeQuery();

			if (rs.next()) {	            
				prod.setPid(rs.getInt("pid"));	  
				prod.setProductname(rs.getString("productname"));
				prod.setPrice(rs.getDouble("price"));
				prod.setQuantity(rs.getInt("quantity"));
				prod.setSid(rs.getInt("sid"));
			}
			//5. close connection
			con.close();
		
		}catch(Exception e) {
			e.printStackTrace();
		
		}

		return prod;
	}

	//delete product
	public void deleteProduct(int pid) {
		try {
			//call getConnection() method 
			con = ConnectionManager.getConnection();
			//3. create statement 
			ps=con.prepareStatement("delete from product where pid=?");
			ps.setInt(1, pid);
			//4. execute query
			ps.executeUpdate();
			//5. close connection
			con.close();
		
		}catch(Exception e) {
			e.printStackTrace();
		
		}
	}

	//update product
	public void updateProduct(Product bean) {

		pid = bean.getPid();
		productname = bean.getProductname();
		price = bean.getPrice();
		quantity = bean.getQuantity();
		sid = bean.getSid();
				
		String searchQuery = "UPDATE product SET productname= '" + productname + "', price ='" + price + "', quantity='" + quantity + "', sid='" + sid + "' WHERE pid= '" + pid + "'";
		try {
			//call getConnection() method 
			con = ConnectionManager.getConnection();
			//3. create statement 
			stmt = con.createStatement();
			//4. execute query
			stmt.executeUpdate(searchQuery);
			//5. close connection
			con.close();
		
		}catch(Exception e) {
			e.printStackTrace();
		
		}
	}

	//get products and supplier
	public static List<Product> getSupplierProducts() { 
		List<Product> prods = new ArrayList<Product>(); 
		try { 
			//call getConnection() method 
			con = ConnectionManager.getConnection();
			//3. create statement 
			stmt = con.createStatement(); 
			//4. execute query
			rs = stmt.executeQuery("SELECT * FROM product p INNER JOIN supplier s ON p.sid = s.sid");

			while (rs.next()) { 
				Product prod = new Product();
				prod.setPid(rs.getInt("pid"));	  
				prod.setProductname(rs.getString("productname"));
				prod.setPrice(rs.getDouble("price"));
				prod.setQuantity(rs.getInt("quantity"));
				prod.setSid(rs.getInt("sid"));
				prod.setSupplier(SupplierDAO.getSupplierById(rs.getInt("sid")));
				prods.add(prod);

			} 
			//5. close connection
			con.close();
		
		}catch(Exception e) {
			e.printStackTrace();
		
		}

		return prods; 
	}

}
