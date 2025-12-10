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
.success-message {
    background-color: #4CAF50;
    color: white;
    padding: 15px;
    margin: 10px auto;
    border-radius: 5px;
    max-width: 400px;
    font-weight: bold;
}
</style>
  </head>
  
  <body>
        <%
            String message = (String) request.getAttribute("message");
            if (message != null) {
        %>
            <div class="success-message"><%= message %></div>
        <% } %>

        <p><b>Sign in </b></p>
        <form action="auth" method="post" onsubmit="return validateLogin()">
        <input type="text" class="form-control" name="username" id="loginUsername" placeholder="User ID"
               maxlength="50" required>
                <br>
            <input type="password" class="form-control" name="password" id="loginPassword" placeholder="Password"
                   maxlength="100" required>
         		<br>
              <button type="submit" class="btn btn-primary btn-block " value="submit">Sign In</button>           
        </form>
        <br>
        <p><a href="<%= request.getContextPath() %>/user/signup" style="color: #333; font-weight: bold;">Don't have an account? Sign Up</a></p>
        <br>
        <a href="<%= request.getContextPath() %>/" class="back-btn">Back to Main Menu</a>

        <script>
        function validateLogin() {
            var username = document.getElementById("loginUsername").value;
            var password = document.getElementById("loginPassword").value;

            // Basic validation to prevent empty or malicious input
            if (!username || username.trim().length === 0) {
                alert("Please enter your username.");
                return false;
            }
            if (!password || password.trim().length === 0) {
                alert("Please enter your password.");
                return false;
            }
            if (username.length > 50) {
                alert("Username is too long.");
                return false;
            }
            if (password.length > 100) {
                alert("Password is too long.");
                return false;
            }

            return true;
        }
        </script>
  </body>
</html>
