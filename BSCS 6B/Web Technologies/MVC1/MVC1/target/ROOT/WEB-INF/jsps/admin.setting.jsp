<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="pk.edu.zab.model.UserModel" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Profile</title>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 20px;
        background-color: #f4f4f4;
    }
    .container {
        max-width: 1200px;
        margin: 0 auto;
        background: white;
        padding: 20px;
        border-radius: 5px;
        box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }
    h2 {
        color: #333;
        border-bottom: 2px solid #007bff;
        padding-bottom: 10px;
    }
    .header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 20px;
    }
    .logout-btn {
        padding: 8px 15px;
        background-color: #dc3545;
        color: white;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        text-decoration: none;
    }
    .logout-btn:hover {
        background-color: #c82333;
    }
    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }
    th {
        background-color: #007bff;
        color: white;
        padding: 12px;
        text-align: left;
        font-weight: bold;
    }
    td {
        padding: 10px;
        border-bottom: 1px solid #ddd;
    }
    tr:hover {
        background-color: #f5f5f5;
    }
    tr:nth-child(even) {
        background-color: #f9f9f9;
    }
    .no-data {
        text-align: center;
        padding: 20px;
        color: #666;
    }
</style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h2>Admin Dashboard - All Users</h2>
            <a href="logout" class="logout-btn">Logout</a>
        </div>

        <p>Welcome, <strong><%= session.getAttribute("AdminName") %></strong>!</p>

        <%
            List<UserModel> userList = (List<UserModel>) request.getAttribute("userList");
            if (userList != null && !userList.isEmpty()) {
        %>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Username</th>
                    <th>Email</th>
                    <th>Password</th>
                    <th>First Name</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (UserModel user : userList) {
                %>
                <tr>
                    <td><%= user.getIduser() %></td>
                    <td><%= user.getUser() %></td>
                    <td><%= user.getEmail() %></td>
                    <td><%= user.getPass() %></td>
                    <td><%= user.getFname() %></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <p style="margin-top: 20px; color: #666;">Total Users: <strong><%= userList.size() %></strong></p>
        <%
            } else {
        %>
        <div class="no-data">
            <p>No users found in the database.</p>
        </div>
        <%
            }
        %>
    </div>
</body>
</html>