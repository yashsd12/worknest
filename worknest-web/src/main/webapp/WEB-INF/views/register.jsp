<html><head><title>Register</title></head>
<body>
<h2>Register (USER)</h2>
<form action="register" method="post">
  <label>Name: <input type="text" name="name" required/></label><br/>
  <label>Email: <input type="email" name="email" required/></label><br/>
  <label>Password: <input type="password" name="password" required/></label><br/>
  <button type="submit">Register</button>
</form>
<p style="color:red;">${error}</p>
<p style="color:green;">${msg}</p>
<p><a href="login">Back to Login</a></p>
</body></html>
