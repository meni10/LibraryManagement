<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="LibraryManagement.DBConnection" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Book</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f9;
            margin: 0;
            padding: 40px;
        }

        .container {
            width: 400px;
            margin: 50px auto;
            background: white;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 0 10px #ccc;
        }

        h2 {
            text-align: center;
            color: #333;
        }

        label {
            display: block;
            margin-top: 20px;
        }

        select {
            width: 100%;
            padding: 10px;
            margin-top: 8px;
        }

        button {
            width: 100%;
            padding: 12px;
            margin-top: 20px;
            background-color: #dc3545;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .back {
            display: block;
            text-align: center;
            margin-top: 20px;
            text-decoration: none;
        }
    </style>
</head>

<body>
    <div class="container">
        <h2>Delete Book</h2>

        <form action="<%= request.getContextPath() %>/deleteBook"
              method="post"
              onsubmit="return confirm('Are you sure you want to delete this book?');">

            <label for="book_id">Select Book Name:</label>

            <select id="book_id" name="book_id" required>
                <option value="">-- Select a Book --</option>

                <%
                    String sql =
                        "SELECT book_id, title FROM books ORDER BY title";

                    try (Connection con = DBConnection.getConnection();
                         PreparedStatement ps = con.prepareStatement(sql);
                         ResultSet rs = ps.executeQuery()) {

                        boolean found = false;

                        while (rs.next()) {
                            found = true;
                %>

                <option value="<%= rs.getInt("book_id") %>">
                    <%= rs.getString("title") %>
                </option>

                <%
                        }

                        if (!found) {
                %>

                <option value="" disabled>No books available</option>

                <%
                        }
                    } catch (Exception e) {
                        application.log("Unable to load books for deletion.", e);
                %>

                <option value="" disabled>Unable to load books</option>

                <%
                    }
                %>
            </select>

            <button type="submit">Delete Book</button>
        </form>

        <a class="back" href="admin-dashboard.html">Back to Home</a>
    </div>
</body>
</html>
