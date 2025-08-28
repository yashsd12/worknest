<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html><head><title>Users</title></head>
<body>
<h2>Users</h2>
<p><a href="users/new">+ Add User</a> | <a href="dashboard">Back</a></p>
<table border="1" cellpadding="5">
  <tr><th>ID</th><th>Name</th><th>Email</th><th>Role</th><th>Actions</th></tr>
  <c:forEach var="u" items="${users}">
    <tr>
      <td>${u.id}</td><td>${u.name}</td><td>${u.email}</td><td>${u.role}</td>
      <td><a href="users/delete/${u.id}">Delete</a></td>
    </tr>
  </c:forEach>
</table>
</body></html>
