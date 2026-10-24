package LibraryManagement;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register-library")
public class RegisterServlet extends HttpServlet {
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		RegisterDAO DAO = new RegisterDAO();
		
		boolean result = DAO.register(name,email,password);
		
		
		if(result) {
			
			resp.sendRedirect("login.html");
		}
		else {
			out.println("registeration failed!");
		}
	}

}
