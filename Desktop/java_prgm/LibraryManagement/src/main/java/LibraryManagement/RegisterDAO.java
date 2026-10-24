package LibraryManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class RegisterDAO {

	public boolean register(String name, String email, String password) {
		
		String url = "jdbc:mysql://localhost:3306/library_management";
		String dbusername = "root";
		String dbpassword = "tiger";
		
		
		String sql = "Insert into users(name,email,password_hash) values(?,?,?)";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = DBConnection.getConnection();
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1,name);
			ps.setString(2, email);
			ps.setString(3, password);
			
			int rows = ps.executeUpdate();
			
			ps.close();
			con.close();
			
			return rows>0;
		}
		
		catch(Exception e) {
			
			 e.printStackTrace();
	         return false;
		}
		
	}
	
}
