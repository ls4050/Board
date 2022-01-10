<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOGIN</title>
</head>
<body>
<div style="text-align:center">
<form method="POST" action="/bbs/fboard">
	<h1>LOGIN</h1>
	<hr>
	<input type="hidden" name="action" value="login">
	<input type = "text" placeholder = "ID" name = "id"/><br>
	<input type = "password" placeholder = "PASSWORD" name="pwd"/><br>
	<input type = "submit" value="LOGIN"/>
</form>
</div>
<div style="text-align:center">
<form method="post" action="/bbs/jspsrc/signUp.jsp">
	<input type = "submit" value="SIGN UP"/>
</form>
</div>
</body>
</html>