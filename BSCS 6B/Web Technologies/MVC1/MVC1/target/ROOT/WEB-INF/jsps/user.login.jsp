<!DOCTYPE html>
<html>
  <head>
    <title>Login Page</title>
    <style type="text/css">
body{
 background-color:#00e2ff;
 color:black;
 text-align: center;
font-style: italic;

}
button{
background-color:orange;
}
input{
background-color: brown;
}
.back-btn {
    background-color: #6c757d;
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    margin-top: 15px;
    text-decoration: none;
    display: inline-block;
}
.back-btn:hover {
    background-color: #5a6268;
}
</style>
  </head>
  
  <body>
        <p><b>Sign in </b></p>
        <form action="auth" method="post">
        <input type="text" class="form-control" name="username" placeholder="User ID">
                <br>      
            <input type="password" class="form-control" name="password"  placeholder="Password">
         		<br>
              <button type="submit" class="btn btn-primary btn-block " value="submit">Sign In</button>           
        </form>
        <br>
        <a href="<%= request.getContextPath() %>/" class="back-btn">Back to Main Menu</a>
  </body>
</html>
