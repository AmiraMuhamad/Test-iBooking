package supplier.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import supplier.connection.ConnectionManager;
import supplier.model.Supplier;

public class SupplierDAO {

	static Connection con = null;
	static ResultSet rs = null; 
	static PreparedStatement ps=null;
	static Statement stmt=null;
	String name, address,city, state, country,phoneno;
	int sid, postcode, id; 

	//list supplier
	public static List<Supplier> getAllSupplier() {
		List<Supplier> sups = new ArrayList<Supplier>();
		try {
			//call getConnection() method
			con = ConnectionManager.getConnection();
			//3. create statement  
			stmt = con.createStatement();
			//4. execute query
			rs = stmt.executeQuery("select * from supplier order by sid");

			while (rs.next()) {
				Supplier sup = new Supplier();
				sup.setSid(rs.getInt("sid"));
				sup.setName(rs.getString("name"));
				sup.setAddress(rs.getString("address"));
				sup.setCity(rs.getString("city"));
				sup.setPostcode(rs.getInt("postcode"));
				sup.setState(rs.getString("state"));
				sup.setCountry(rs.getString("country"));				
				sup.setPhoneno(rs.getString("phoneno"));
				sup.setId(rs.getInt("id"));
				sups.add(sup);
			}
			//5. close connection
			con.close();
		}catch(Exception e) {
			e.printStackTrace();		
		}
		return sups;
	}


	//get supplier by sid
	public static Supplier getSupplierById(int sid) {
		Supplier sup = new Supplier();
		try {
			//call getConnection() method  
			con = ConnectionManager.getConnection();
			//3. create statement 
			ps=con.prepareStatement("select * from supplier where sid=?");
			ps.setInt(1, sid);
			 //4. execute query
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {	            
				sup.setSid(rs.getInt("sid"));
				sup.setName(rs.getString("name"));
				sup.setAddress(rs.getString("address"));
				sup.setCity(rs.getString("city"));
				sup.setPostcode(rs.getInt("postcode"));
				sup.setState(rs.getString("state"));
				sup.setCountry(rs.getString("country"));				
				sup.setPhoneno(rs.getString("phoneno"));
				sup.setId(rs.getInt("id"));
			}
			//5. close connection
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		
		}

		return sup;
	}

	//get supplier by Id
	public static Supplier getSupplierId(int id) {
		Supplier sup = new Supplier();
		try {
			//call getConnection() method  
			con = ConnectionManager.getConnection();
			//3. create statement  
			ps=con.prepareStatement("select * from supplier where id=?");
			ps.setInt(1, id);
			//4. execute query
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {	            
				sup.setSid(rs.getInt("sid"));
				sup.setName(rs.getString("name"));
				sup.setAddress(rs.getString("address"));
				sup.setCity(rs.getString("city"));
				sup.setPostcode(rs.getInt("postcode"));
				sup.setState(rs.getString("state"));
				sup.setCountry(rs.getString("country"));				
				sup.setPhoneno(rs.getString("phoneno"));
				sup.setId(rs.getInt("id"));
			}
			//5. close connection
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		
		}

		return sup;
	}


	public static List<Supplier> getSupplierId() {
		List<Supplier> sups = new ArrayList<Supplier>();
		try {
			//call getConnection() method  			
			con = ConnectionManager.getConnection();
			//3. create statement  
			stmt = con.createStatement();
			//4. execute query
			ResultSet rs = stmt.executeQuery("select sid, name  from supplier");

			while (rs.next()) {
				Supplier sup = new Supplier();
				sup.setSid(rs.getInt("sid"));
				sup.setName(rs.getString("name"));
				sups.add(sup);

			}
			//5. close connection
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		
		}

		return sups;
	}

	//update supplier
	public void updateSupplier(Supplier bean) {

		sid = bean.getSid();
		name = bean.getName();
		address = bean.getAddress();
		city = bean.getCity();
		postcode = bean.getPostcode();
		state = bean.getState();
		country = bean.getCountry();		
		phoneno = bean.getPhoneno(); 		

		try {
			//call getConnection() method  
			con = ConnectionManager.getConnection();
			//3. create statement  
			ps=con.prepareStatement("update supplier set name=?,address=?,city=?,postcode=?,state=?,country=?,phoneno=? WHERE sid=?"); 		  
			ps.setString(1,name);//1 specifies the first parameter in the query i.e. name
			ps.setString(2,address);		
			ps.setString(3,city);
			ps.setInt(4,postcode);
			ps.setString(5,state);
			ps.setString(6,country);			
			ps.setString(7,phoneno);				
			ps.setInt(8,sid);
			//4. execute query
			ps.executeUpdate();

			//5. close connection
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		
		}
	}

	
}
