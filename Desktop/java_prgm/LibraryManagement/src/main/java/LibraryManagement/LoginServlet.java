package LibraryManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login-library")
public class LoginServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		
		
		String sql = "Select * from users where email=? and password_hash = ?";
		
		
		try (Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
        		 
        		 ps.setString(1, email);
        		 ps.setString(2, password);
        		 
        		 try(ResultSet rs = ps.executeQuery()){
        			 
        			 if(rs.next()) {
        				 
        				 String role = rs.getString("role");
        				 
        				 HttpSession session = req.getSession();
        				 session.setAttribute("email", email);
        				 session.setAttribute("role", role);
        				 
        				 if ("ADMIN".equalsIgnoreCase(role)) {
        					    resp.sendRedirect("admin-dashboard.html");
        					} else {
        					    resp.sendRedirect("user-dashboard.html");
        					}
        				 
        				 
        			 }
        			 else {
                         out.println("<h1>Invalid credentials</h1>");
                         req.getRequestDispatcher("Login.html").include(req, resp);
                         }
        		 }
        		 
        		 
        	 }
			catch (Exception e) {
	            e.printStackTrace();
	            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	            out.println("Unable to check login. Please try again.");
        	 }
		
		}
}

