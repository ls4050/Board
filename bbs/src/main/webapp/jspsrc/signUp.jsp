<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="POST" action="/bbs/fboard">
	<h1>회원 정보를 입력하십시오.</h1>
	<hr>
	<input type = "hidden" name="action" value="signup">
	<input type = "text" placeholder = "ID" name = "id"/><br>
	<input type = "password" placeholder = "PASSWORD" name="pwd"/><br>
	<input type = "text" placeholder = "이름을 입력하세요" name = "name"/> <br>
	<input type = "text" placeholder = "전화번호를 입력하세요" name = "phone"/><br>
	<input type = "submit" value="등록"/>
	<input type = "reset" value="재작성"/>
</form>
</body>
</html>