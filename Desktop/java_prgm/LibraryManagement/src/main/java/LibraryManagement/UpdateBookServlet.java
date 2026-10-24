
package LibraryManagement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/updateBook")
public class UpdateBookServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
    	if(session == null || session.getAttribute("email") == null) {
    		resp.sendRedirect("login.html");
    		return;
    	}
		 req.getRequestDispatcher("/updatebook.jsp")
	       .forward(req, resp);
	}

    @Override
    protected void doPost(HttpServletRequest req,
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
    	

        req.setCharacterEncoding("UTF-8");

        try {
            int bookId = Integer.parseInt(req.getParameter("book_id"));
            String title = req.getParameter("title");
            String author = req.getParameter("author");
            String category = req.getParameter("category");
            int totalCopies = Integer.parseInt(
                    req.getParameter("total_copies"));
            int availableCopies = Integer.parseInt(
                    req.getParameter("available_copies"));
            String bookUrl = req.getParameter("book_url");
            String bookDesc = req.getParameter("book_desc");

            if (totalCopies < 1 || availableCopies < 0
                    || availableCopies > totalCopies) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
                        "Check the total and available copies.");
                return;
            }

            String sql = "UPDATE books SET title = ?, author = ?, "
                       + "category = ?, total_copies = ?, "
                       + "available_copies = ?, book_url = ?, "
                       + "book_desc = ? WHERE book_id = ?";

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, title);
                ps.setString(2, author);
                ps.setString(3, category);
                ps.setInt(4, totalCopies);
                ps.setInt(5, availableCopies);
                ps.setString(6, bookUrl);
                ps.setString(7, bookDesc);
                ps.setInt(8, bookId);

                int rowsUpdated = ps.executeUpdate();

                if (rowsUpdated > 0) {
                    resp.sendRedirect(
                            req.getContextPath() + "/viewbook");
                } else {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                            "Book not found. No changes were saved.");
                }
            }

        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Invalid book ID or copy count.");
        } catch (Exception e) {
            throw new ServletException("Unable to update book.", e);
        }
    }
}
