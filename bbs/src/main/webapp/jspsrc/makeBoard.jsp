<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
</head>
<body>
	<h1 style="text-align:center">게시글 작성</h1>
	<hr>
	<form method="post" action="/bbs/fboard">
		<input type="hidden" name="action" value="insert">
      <table>
         <tr>
            <th>글 제목</th>
            <th><input type="text" name="title" required style="width: 933px; border:0;"></th>
         </tr>
         <tr>
            <td>글 내용</td>
            <td><textarea name="content"
                  required style="margin: 0px; width: 933px; height: 508px;"></textarea></td>
                  
         </tr>
      </table>
      <br> 
      <div style="text-align:right;  margin-right: 15%;">
      <input type="submit" value="등록"> <input type="reset"
         value="재작성">
      </div>
	</form>
</body>
<Style>
table {
	width: 70%;
	margin: auto;
}

table, td, th {
	border-collapse: collapse;
	border: 1px solid black;
	text-align: center;
}
;
</Style>
</html>