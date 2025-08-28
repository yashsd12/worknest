<html><head><title>Add User</title></head>
<body>
<h2>Add User</h2>
<form action="save" method="post">
  <label>Name: <input type="text" name="name" required/></label><br/>
  <label>Email: <input type="email" name="email" required/></label><br/>
  <label>Password: <input type="password" name="password" required/></label><br/>
  <label>Role:
    <select name="role">
      <option>USER</option>
      <option>ADMIN</option>
    </select>
  </label><br/>
  <button type="submit">Save</button>
</form>
<p><a href="../users">Back</a></p>
</body></html>
