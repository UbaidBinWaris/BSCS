<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.jpalearning.entity.User" %>
<%
    // Check if user is logged in
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard - JPA Learning</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            padding: 20px;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            background: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 10px 25px rgba(0,0,0,0.2);
        }
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 30px;
            padding-bottom: 20px;
            border-bottom: 2px solid #eee;
        }
        h1 {
            color: #333;
        }
        .logout-btn {
            padding: 10px 20px;
            background: #e74c3c;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-weight: bold;
        }
        .logout-btn:hover {
            background: #c0392b;
        }
        .user-info {
            background: #f8f9fa;
            padding: 20px;
            border-radius: 5px;
            margin-bottom: 20px;
        }
        .user-info h2 {
            color: #667eea;
            margin-bottom: 15px;
        }
        .info-row {
            padding: 10px 0;
            border-bottom: 1px solid #ddd;
        }
        .info-row:last-child {
            border-bottom: none;
        }
        .label {
            font-weight: bold;
            color: #555;
            display: inline-block;
            width: 150px;
        }
        .value {
            color: #333;
        }
        .welcome {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 30px;
            border-radius: 5px;
            text-align: center;
        }
        .welcome h2 {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>Dashboard</h1>
            <a href="${pageContext.request.contextPath}/app/logout" class="logout-btn">Logout</a>
        </div>

        <div class="welcome">
            <h2>Welcome, <%= user.getUsername() %>!</h2>
            <p>You have successfully logged in to your account.</p>
        </div>

        <div class="user-info">
            <h2>Your Profile Information</h2>
            <div class="info-row">
                <span class="label">User ID:</span>
                <span class="value"><%= user.getId() %></span>
            </div>
            <div class="info-row">
                <span class="label">Username:</span>
                <span class="value"><%= user.getUsername() %></span>
            </div>
            <div class="info-row">
                <span class="label">Email:</span>
                <span class="value"><%= user.getEmail() %></span>
            </div>
            <div class="info-row">
                <span class="label">Account Created:</span>
                <span class="value"><%= user.getCreatedAt() %></span>
            </div>
        </div>
    </div>
</body>
</html>

