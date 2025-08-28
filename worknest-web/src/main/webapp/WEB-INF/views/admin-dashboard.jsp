<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html><head><title>Admin Dashboard</title></head>
<body>
<h2>Admin Dashboard</h2>
<p>
  Pending: ${pending} |
  In Progress: ${inprogress} |
  Completed: ${completed} |
  Delayed: ${delayed}
</p>
<p>
  <a href="users">Manage Users</a> |
  <a href="tasks/new">Create & Assign Task</a> |
  <a href="../logout">Logout</a>
</p>
</body></html>
