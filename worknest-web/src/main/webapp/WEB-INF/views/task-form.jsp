<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html><head><title>Create Task</title></head>
<body>
<h2>Create Task & Assign</h2>
<form action="save" method="post">
  <label>Title: <input type="text" name="title" required/></label><br/>
  <label>Description:<br/><textarea name="description" rows="4" cols="50"></textarea></label><br/>
  <label>Start Date: <input type="date" name="startDate" required/></label><br/>
  <label>Due Date: <input type="date" name="dueDate" required/></label><br/>

  <label>Assign to Users (Ctrl/Shift for multi-select):</label><br/>
  <select name="userIds" multiple size="8" required>
    <c:forEach var="u" items="${users}">
      <option value="${u.id}">${u.name} (${u.email})</option>
    </c:forEach>
  </select>
  <br/><br/>
  <button type="submit">Create & Assign</button>
</form>
<p><a href="../dashboard">Back</a></p>
</body></html>
