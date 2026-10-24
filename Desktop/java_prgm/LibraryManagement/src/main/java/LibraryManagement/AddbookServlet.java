package LibraryManagement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/addBook")
public class AddbookServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req,
	                     HttpServletResponse resp)
	        throws ServletException, IOException {
		
		HttpSession session = req.getSession(false);
    	if(session == null || session.getAttribute("email") == null) {
    		resp.sendRedirect("login.html");
    		return;
    	}
    	
    	if (!"ADMIN".equals(session.getAttribute("role"))) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN,
                           "Only admin can delete books.");
            return;
        }
    	

	    req.getRequestDispatcher("/addbook.html")
	       .forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession(false);
    	if(session == null || session.getAttribute("email") == null) {
    		resp.sendRedirect("login.html");
    		return;
    	}
    	
    	if (!"ADMIN".equals(session.getAttribute("role"))) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN,
                           "Only admin can delete books.");
            return;
        }
		
		String title = req.getParameter("title");
		String author = req.getParameter("author");
		String category = req.getParameter("category");
		String total_copies = req.getParameter("total_copies");
		String available_copies = req.getParameter("available_copies");
		String book_url = req.getParameter("book_url");
		String book_desc = req.getParameter("book_desc");
		
		String  sql = "Insert Into books (title, author,category,total_copies,available_copies,book_url, book_desc) values (?,?,?,?,?,?,?)";
		
		try(Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
				ps.setString(1, title);
				ps.setString(2, author);
				ps.setString(3, category);
				ps.setString(4,total_copies);
				ps.setString(5, available_copies);
				ps.setString(6, book_url);
				ps.setString(7, book_desc);
				
				
				int rows = ps.executeUpdate();
				
				if(rows>0) {
					System.out.println("Books Inserted successfully!");
				
				}
				
				}
		 catch (Exception e) {
			    e.printStackTrace();
	}

}
}
