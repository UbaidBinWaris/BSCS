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
<title>Setting</title>
<style type="text/css">
body{

text-align: center;
color:white;
background-color: #ce4abd;
}
button{
background-color: orange;
}
</style>
</head>
<body>
	<h4>This is user Setting Page</h4>
	  NAME: Ubaid Bin Waris
	  <br>
	  REGNO:2212416
	  <br>
	   
	  
	  <br>
	  
	<form action="welcome" method="post">
              <button type="submit" class="btn btn-primary btn-block " value="submit">previousPage</button>
        </form>
</body>
</html>