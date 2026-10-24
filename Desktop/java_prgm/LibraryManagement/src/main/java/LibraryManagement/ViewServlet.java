
package LibraryManagement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/viewbook")
public class ViewServlet extends HttpServlet {

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

    	List<book> bookList = new ArrayList<>();

        String sql = "SELECT book_id, title, author, book_url, book_desc "
                   + "FROM books";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                book b = new book(
                    rs.getInt("book_id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("book_url"),
                    rs.getString("book_desc")
                );

                bookList.add(b);
            }

            req.setAttribute("book", bookList);

            req.getRequestDispatcher("/viewbook.jsp")
               .forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();

            throw new ServletException(
                "Unable to retrieve books from database", e
            );
        }
    }
}
