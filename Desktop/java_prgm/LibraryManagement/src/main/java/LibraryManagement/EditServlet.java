
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

@WebServlet("/editBook")
public class EditServlet extends HttpServlet {

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

        String id = req.getParameter("book_id");

        if (id == null || id.isBlank()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
                           "Please select a book.");
            return;
        }

        String sql = "SELECT * FROM books WHERE book_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, Integer.parseInt(id));

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    req.setAttribute("book_id", rs.getInt("book_id"));
                    req.setAttribute("title", rs.getString("title"));
                    req.setAttribute("author", rs.getString("author"));
                    req.setAttribute("category", rs.getString("category"));
                    req.setAttribute("total_copies",
                                     rs.getInt("total_copies"));
                    req.setAttribute("available_copies",
                                     rs.getInt("available_copies"));
                    req.setAttribute("book_url", rs.getString("book_url"));
                    req.setAttribute("book_desc", rs.getString("book_desc"));

                    req.getRequestDispatcher("/editbook.jsp")
                       .forward(req, resp);

                } else {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                                   "Book not found.");
                }
            }

        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
                           "Invalid book ID.");
        } catch (Exception e) {
            throw new ServletException("Unable to load book details.", e);
        }
    }
}
