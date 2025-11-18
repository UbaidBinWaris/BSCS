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
  </body>
</html>
