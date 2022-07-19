package supplier.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import supplier.connection.ConnectionManager;
import supplier.model.Staff;

public class StaffDAO {

	static Connection con = null;
	static ResultSet rs = null; 
	static PreparedStatement ps=null;
	static Statement stmt=null;
	String name;
	int staffid; 	

	//list supplier
	public static List<Staff> getAllStaff() {
		List<Staff> staff = new ArrayList<Staff>();
		try {
			//call getConnection() method 
			con = ConnectionManager.getConnection();
			//3. create statement 
			stmt = con.createStatement();
			//4. execute query
			rs = stmt.executeQuery("select * from staff order by staffid");

			while (rs.next()) {
				Staff st = new Staff();
				st.setStaffid(rs.getInt("staffid"));
				st.setName(rs.getString("name"));
				st.setId(rs.getInt("id"));
				staff.add(st);

			}
			//5. close connection
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		
		}

		return staff;
	}


	//get staff by staffid
	public static Staff getStaffById(int staffid) {
		Staff st = new Staff();
		try {
			//call getConnection() method 
			con = ConnectionManager.getConnection();
			//3. create statement 
			ps=con.prepareStatement("select * from staff where staffid=?");
			ps.setInt(1, staffid);
			//4. execute query
			rs = ps.executeQuery();
			if (rs.next()) {	            
				st.setStaffid(rs.getInt("staffid"));
				st.setName(rs.getString("name"));
				st.setId(rs.getInt("id"));
			}
			//5. close connection
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		
		}

		return st;
	}

	//get staff by Id
	public static Staff getStaffId(int id) {
		
		Staff st = new Staff();
		try {
			//call getConnection() method 
			con = ConnectionManager.getConnection();
			//3. create statement 
			ps=con.prepareStatement("select * from staff where id=?");
			ps.setInt(1, id);
			//4. execute query
			rs = ps.executeQuery();

			if (rs.next()) {	            
				st.setStaffid(rs.getInt("sid"));
				st.setName(rs.getString("name"));
				st.setId(rs.getInt("id"));
			}
			//5. close connection
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		
		}

		return st;
	}


	public static List<Staff> getStaffId() {
		List<Staff> staff = new ArrayList<Staff>();
		try {
			//call getConnection() method 
			con = ConnectionManager.getConnection();
			//3. create statement 
			stmt = con.createStatement();
			//4. execute query
			rs = stmt.executeQuery("select staffid, name  from staff");
			while (rs.next()) {
				Staff st = new Staff();
				st.setStaffid(rs.getInt("staffid"));
				st.setName(rs.getString("name"));
				staff.add(st);

			}
			//5. close connection
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		
		}

		return staff;
	}

	//update staff
	public void updateStaff(Staff bean) {

		staffid = bean.getStaffid();
		name = bean.getName();	

		try {
			//call getConnection() method
			con = ConnectionManager.getConnection();
			//3. create statement 
			ps=con.prepareStatement("update staff set name=? WHERE staffid=?"); 		  
			ps.setString(1,name); //1 specifies the first parameter in the query i.e. name			
			ps.setInt(2,staffid);
			//4. execute query
			ps.executeUpdate();
			//5. close connection
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		
		}
	}

	
}
