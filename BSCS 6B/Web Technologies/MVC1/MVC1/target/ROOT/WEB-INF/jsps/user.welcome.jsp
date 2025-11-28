<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
// Prevent caching
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);

// Check if user is logged in
if(session.getAttribute("UserName") == null) {
    response.sendRedirect("login");
    return;
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Welcome</title>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 20px;
        background-color: #f4f4f4;
        padding: 20px;
    }
    .container {
        max-width: 600px;
        margin: 50px auto;
        background: white;
        padding: 30px;
        border-radius: 5px;
        box-shadow: 0 0 10px rgba(0,0,0,0.1);
        text-align: center;
    }
    h2 {
        color: #333;
        margin-bottom: 30px;
    }
    button {
        background-color: orange;
        color: white;
        padding: 12px 30px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        font-size: 16px;
        margin: 10px;
        width: 200px;
    }
    button:hover {
        background-color: #e69500;
    }
    input {
        background-color: brown;
        color: white;
        padding: 10px;
        border: none;
        border-radius: 4px;
    }
    .welcome-message {
        font-size: 24px;
        color: #333;
        margin-bottom: 20px;
    }
</style>
</head>
<body>
    <div class="container">
        <h2 class="welcome-message">Welcome, <%= session.getAttribute("UserName") %>!</h2>

        <form action="setting" method="post">
            <button type="submit" class="btn btn-primary btn-block">User Setting</button>
        </form>

        <form action="logout" method="post">
            <button type="submit" class="btn btn-primary btn-block" value="submit">Logout</button>
        </form>
    </div>
</body>
</html>