
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

@WebServlet("/deleteBook")
public class DeleteServlet extends HttpServlet {

    // Display the delete-book page
    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("DeleteServlet doGet is running!");

        HttpSession session = req.getSession(false);

        if (session == null ||
            session.getAttribute("email") == null) {
            resp.sendRedirect(req.getContextPath() + "/login.html");
            return;
        }

        if (!"ADMIN".equals(session.getAttribute("role"))) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN,
                           "Only admin can delete books.");
            return;
        }

        req.getRequestDispatcher("/deletebook.jsp")
           .forward(req, resp);
    }

    // Delete the selected book
    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("DeleteServlet doPost is running!");

        HttpSession session = req.getSession(false);

        if (session == null ||
            session.getAttribute("email") == null) {
            resp.sendRedirect(req.getContextPath() + "/login.html");
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

        int bookId;

        try {
            bookId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
                           "Invalid book ID.");
            return;
        }

        String sql = "DELETE FROM books WHERE book_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, bookId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                resp.sendRedirect(
                    req.getContextPath() + "/deleteBook?deleted=true");
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                               "Book not found.");
            }

        } catch (java.sql.SQLException e) {
            throw new ServletException(
                "Unable to delete the book. Check whether borrowing records reference it.",
                e);
        } catch (Exception e) {
            throw new ServletException("Unable to connect to the database.", e);
        }
    }
}
