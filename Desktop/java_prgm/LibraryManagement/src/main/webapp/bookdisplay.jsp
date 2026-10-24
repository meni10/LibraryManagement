
<%@ page import="LibraryManagement.book" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Explore Books</title>

    <style>
        
* {
    box-sizing: border-box;
}

body {
    margin: 0;
    padding: 0;
    font-family: "Segoe UI", Arial, sans-serif;
    background: #f5f7f6;
    color: #26352f;
}

/* Page title */
h1 {
    text-align: center;
    color: #155b4d;
    font-size: 34px;
    font-weight: 700;
    margin: 35px 0 15px;
    letter-spacing: -0.5px;
}

/* Book cards grid */
.container {
    max-width: 1400px;
    margin: 0 auto;
    padding: 25px 35px 45px;
    display: grid;
    grid-template-columns: repeat(3, minmax(0, 1fr));
    gap: 26px;
    align-items: stretch;
}

/* Individual card */
.minicontainer {
    background: #ffffff;
    border: 1px solid #e5ece8;
    border-radius: 14px;
    padding: 24px;
    min-width: 0;
    display: flex;
    flex-direction: column;
    box-shadow: 0 4px 16px rgba(25, 65, 53, 0.06);
    transition: transform 0.25s ease,
                box-shadow 0.25s ease,
                border-color 0.25s ease;
}

.minicontainer:hover {
    transform: translateY(-5px);
    box-shadow: 0 12px 28px rgba(25, 65, 53, 0.12);
    border-color: #c7e1d6;
}

/* Book title */
.name {
    color: #155b4d;
    font-size: 21px;
    font-weight: 700;
    line-height: 1.4;
    margin-bottom: 16px;
    overflow-wrap: anywhere;
}

/* Book cover area */
.url {
    width: 100%;
    height: 245px;
    padding: 14px;
    margin-bottom: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
    background: #f7f9f8;
    border: 1px solid #eef2f0;
    border-radius: 10px;
}

.url img {
    display: block;
    max-width: 100%;
    max-height: 215px;
    width: auto;
    height: auto;
    object-fit: contain;
    border-radius: 5px;
}

/* Description */
.author {
    color: #5d6964;
    font-size: 14px;
    line-height: 1.8;
    margin-top: 0;
    margin-bottom: 20px;
    overflow-wrap: anywhere;
}

.desc p {
    margin: 0;
}

/* Author */
.desc{
    margin-top: auto;
    padding-top: 13px;
    border-top: 1px solid #edf1ef;
    color: #176b55;
    font-size: 13px;
    font-weight: 600;
}

.author p {
    margin: 0;
}

/* Empty message */
.empty-message {
    grid-column: 1 / -1;
    text-align: center;
    padding: 30px 20px;
    color: #687770;
    font-size: 16px;
    background: #ffffff;
    border: 1px solid #e5ece8;
    border-radius: 12px;
}

/* Tablet */
@media (max-width: 1000px) {
    .container {
        grid-template-columns: repeat(2, minmax(0, 1fr));
        padding: 22px;
        gap: 20px;
    }
}

/* Mobile */
@media (max-width: 600px) {
    h1 {
        font-size: 28px;
        margin: 25px 0 10px;
    }

    .container {
        grid-template-columns: 1fr;
        padding: 18px;
        gap: 18px;
    }

    .minicontainer {
        padding: 20px;
    }

    .url {
        height: 230px;
    }

    .url img {
        max-height: 200px;
    }
}
        
    </style>
</head>

<body>

    <h1>Explore Books</h1>

    <div class="container">

        <%
            List<book> bk =
                (List<book>) request.getAttribute("book");
        %>

        <% if (bk != null && !bk.isEmpty()) { %>

            <% for (book b : bk) { %>

                <div class="minicontainer">

                  
                    <div class="name">
                        <%= b.getBookname() %>
                    </div>

                    <div class="url">
                        <img src="<%= b.getBookurl() %>"
                             alt="<%= b.getBookname() %>">
                    </div>

                    <div class="desc">
                        <p><%= b.getBookdesc() %></p>
                    </div>

                    <div class="author">
                        <p>Author: <%= b.getauthor()%></p>
                    </div>

                </div>

            <% } %>

        <% } else { %>

            <p class="empty-message">
                No books available to display.
            </p>

        <% } %>

    </div>

</body>
</html>
