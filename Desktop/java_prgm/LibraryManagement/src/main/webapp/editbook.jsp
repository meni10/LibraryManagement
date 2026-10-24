<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Book Details</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f5f7f6;
            padding: 25px;
        }

        .container {
            max-width: 550px;
            margin: 20px auto;
            padding: 25px;
            background: white;
            border-radius: 12px;
        }

        h2 {
            text-align: center;
            color: #155b4d;
        }

        label {
            display: block;
            margin-top: 15px;
            font-weight: bold;
        }

        input, textarea {
            width: 100%;
            padding: 10px;
            margin-top: 6px;
            box-sizing: border-box;
        }

        textarea {
            min-height: 90px;
        }

        button {
            width: 100%;
            margin-top: 22px;
            padding: 12px;
            background: #155b4d;
            color: white;
            border: none;
            cursor: pointer;
        }
    </style>
</head>

<body>

<div class="container">

    <h2>Edit Book Details</h2>

    <form action="<%= request.getContextPath() %>/updateBook" method="post">

        <input type="hidden" name="book_id"
               value="<%= request.getAttribute("book_id") %>">

        <label>Book Title</label>
        <input type="text" name="title"
               value="<%= request.getAttribute("title") %>"
               maxlength="200" required>

        <label>Author</label>
        <input type="text" name="author"
               value="<%= request.getAttribute("author") %>"
               maxlength="150" required>

        <label>Category</label>
        <input type="text" name="category"
               value="<%= request.getAttribute("category") == null ? "" : request.getAttribute("category") %>">

        <label>Total Copies</label>
        <input type="number" name="total_copies"
               min="1" value="<%= request.getAttribute("total_copies") %>"
               required>

        <label>Available Copies</label>
        <input type="number" name="available_copies"
               min="0" value="<%= request.getAttribute("available_copies") %>"
               required>

        <label>Book Image URL</label>
        <input type="url" name="book_url"
               value="<%= request.getAttribute("book_url") == null ? "" : request.getAttribute("book_url") %>"
               maxlength="500">

        <label>Description</label>
        <textarea name="book_desc"><%= request.getAttribute("book_desc") == null ? "" : request.getAttribute("book_desc") %></textarea>

        <button type="submit">Save Changes</button>

    </form>

</div>

</body>
</html>
