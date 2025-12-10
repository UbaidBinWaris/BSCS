<!DOCTYPE html>
<html>
  <head>
    <title>Sign Up Page</title>
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
color: white;
padding: 8px;
margin: 5px;
border: 1px solid #333;
border-radius: 4px;
width: 250px;
}
input::placeholder {
    color: #ddd;
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
.signup-btn {
    background-color: orange;
    color: black;
    padding: 10px 30px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    margin-top: 10px;
    font-weight: bold;
}
.signup-btn:hover {
    background-color: #ff8c00;
}
.login-link {
    color: #333;
    text-decoration: none;
    font-weight: bold;
    margin-top: 15px;
    display: inline-block;
}
.login-link:hover {
    color: #000;
}
.error-message {
    background-color: #f44336;
    color: white;
    padding: 15px;
    margin: 10px auto;
    border-radius: 5px;
    max-width: 400px;
    font-weight: bold;
}
.validation-hint {
    font-size: 12px;
    color: #333;
    font-style: normal;
    margin-top: -3px;
    margin-bottom: 5px;
}
</style>
  </head>

  <body>
        <h2><b>Sign Up</b></h2>
        <p>Create a new account</p>

        <%
            String error = (String) request.getAttribute("error");
            if (error != null) {
        %>
            <div class="error-message"><%= error %></div>
        <% } %>

        <form action="register" method="post" onsubmit="return validateForm()">
            <input type="text" name="username" id="username" placeholder="Username"
                   value="<%= request.getAttribute("username") != null ? request.getAttribute("username") : "" %>"
                   minlength="3" maxlength="50" pattern="[a-zA-Z0-9_]+"
                   title="Username can only contain letters, numbers, and underscores" required>
            <div class="validation-hint">3-50 characters, letters, numbers, underscores only</div>

            <input type="password" name="password" id="password" placeholder="Password"
                   minlength="6" maxlength="100" required>
            <div class="validation-hint">Minimum 6 characters</div>

            <input type="email" name="email" id="email" placeholder="Email"
                   value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>"
                   maxlength="100" required>
            <div class="validation-hint">Valid email address required</div>

            <input type="text" name="fname" id="fname" placeholder="Full Name"
                   value="<%= request.getAttribute("fname") != null ? request.getAttribute("fname") : "" %>"
                   minlength="2" maxlength="100" pattern="[a-zA-Z\s]+"
                   title="Full name can only contain letters and spaces" required>
            <div class="validation-hint">2-100 characters, letters and spaces only</div>
            <br>
            <button type="submit" class="signup-btn">Sign Up</button>
        </form>
        <br>
        <a href="<%= request.getContextPath() %>/user/login" class="login-link">Already have an account? Login here</a>
        <br><br>
        <a href="<%= request.getContextPath() %>/" class="back-btn">Back to Main Menu</a>

        <script>
        function validateForm() {
            var username = document.getElementById("username").value;
            var password = document.getElementById("password").value;
            var email = document.getElementById("email").value;
            var fname = document.getElementById("fname").value;

            // Username validation
            if (username.length < 3 || username.length > 50) {
                alert("Username must be between 3 and 50 characters.");
                return false;
            }
            if (!/^[a-zA-Z0-9_]+$/.test(username)) {
                alert("Username can only contain letters, numbers, and underscores.");
                return false;
            }

            // Password validation
            if (password.length < 6 || password.length > 100) {
                alert("Password must be between 6 and 100 characters.");
                return false;
            }

            // Email validation
            if (!/^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/.test(email)) {
                alert("Please enter a valid email address.");
                return false;
            }

            // Full name validation
            if (fname.length < 2 || fname.length > 100) {
                alert("Full name must be between 2 and 100 characters.");
                return false;
            }
            if (!/^[a-zA-Z\s]+$/.test(fname)) {
                alert("Full name can only contain letters and spaces.");
                return false;
            }

            return true;
        }
        </script>
  </body>
</html>
