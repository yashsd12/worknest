<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html><head><title>User Home</title></head>
<body>
<h2>My Assignments</h2>
<p><a href="../logout">Logout</a></p>
<table border="1" cellpadding="5">
  <tr>
    <th>Task</th><th>Start</th><th>Due</th><th>Status</th><th>Actions</th><th>Comments</th>
  </tr>
  <c:forEach var="a" items="${assignments}">
    <tr>
      <td>${a.task.title}</td>
      <td>${a.startDate}</td>
      <td>${a.dueDate}</td>
      <td>${a.status}</td>
      <td>
        <form action="assignment/${a.id}/status" method="post" style="display:inline;">
          <select name="status">
            <option ${a.status=='PENDING'?'selected':''}>PENDING</option>
            <option ${a.status=='IN_PROGRESS'?'selected':''}>IN_PROGRESS</option>
            <option ${a.status=='COMPLETED'?'selected':''}>COMPLETED</option>
            <option ${a.status=='DELAYED'?'selected':''}>DELAYED</option>
          </select>
          <button type="submit">Update</button>
        </form>
      </td>
      <td><a href="assignment/${a.id}">View/Add</a></td>
    </tr>
  </c:forEach>
</table>
</body></html>
