<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
    <style type="text/css">
body{
 background-color:#fff300;
 color:black;
 text-align: center;
font-style: italic;
font-size: 20px;

}
button{
background-color:orange;
}
input{
background-color: brown;
}


}
</style>
</head>
<body>
welcome
<br><br>
		<form action="setting" method="post">
			<button type="submit" class="btn btn-primary btn-block">User Setting</button>
		</form>
<br><br>
		 <form action="logout" method="post">
              <button type="submit" class="btn btn-primary btn-block " value="submit">Logout</button>           
        </form>   
</body>
</html>