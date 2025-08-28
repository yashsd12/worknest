<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html><head><title>Task Details</title></head>
<body>
<h2>Comments</h2>
<form action="comment" method="post">
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> <!-- harmless if no Spring Security -->
</form>

<form action="comment" method="post" action="">
  <form action="comment" method="post"></form>
</body></html>
