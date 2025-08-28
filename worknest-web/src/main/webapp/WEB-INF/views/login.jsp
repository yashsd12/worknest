<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html><head><title>Login</title></head>
<body>
<h2>WorkNest Login</h2>
<form action="login" method="post">
  <label>Email: <input type="email" name="email" required/></label><br/>
  <label>Password: <input type="password" name="password" required/></label><br/>
  <button type="submit">Login</button>
</form>
<p style="color:red;"><c:out value="${error}"/></p>
<p><a href="register">Register (USER)</a></p>
</body></html>
