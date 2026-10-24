
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
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Book</title>

    <style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            padding: 30px 15px;
            font-family: "Segoe UI", Arial, sans-serif;
            background: #f5f7f6;
            color: #26352f;
        }

        .container {
            max-width: 600px;
            margin: 20px auto;
            padding: 30px;
            background: white;
            border: 1px solid #e5ece8;
            border-radius: 14px;
            box-shadow: 0 4px 16px rgba(25, 65, 53, 0.08);
        }

        h1 {
            text-align: center;
            color: #155b4d;
            margin-top: 0;
            margin-bottom: 25px;
        }

        label {
            display: block;
            margin: 15px 0 7px;
            font-weight: 600;
        }

        select {
            width: 100%;
            padding: 12px;
            border: 1px solid #cfdad4;
            border-radius: 7px;
            background: white;
            font-size: 15px;
        }

        button {
            width: 100%;
            margin-top: 25px;
            padding: 13px;
            border: none;
            border-radius: 7px;
            background: #155b4d;
            color: white;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
        }

        button:hover {
            background: #10483d;
        }

        .back-link {
            display: block;
            margin-top: 18px;
            text-align: center;
            color: #155b4d;
            text-decoration: none;
        }

        .message {
            padding: 12px;
            background: #fff4e5;
            border-radius: 7px;
            color: #78551a;
        }
    </style>
</head>

<body>

<div class="container">

    <h1>Update Book</h1>

    <form action="editBook" method="get">

        <label for="book_id">Select Book Name</label>

        <select id="book_id" name="book_id" required>
            <option value="">-- Select a Book --</option>

            <%
                String sql = "SELECT book_id, title FROM books ORDER BY title";

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
                    application.log("Unable to load books for updating.", e);
            %>
                <option value="" disabled>Unable to load books</option>
            <%
                }
            %>

        </select>

        <button type="submit">Load Book Details</button>

    </form>

    <a class="back-link" href="admin-dashboard.html">Back to Admin Home</a>

</div>

</body>
</html>
```