<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.dao.FBoardDAO"%>
<%@ page import="model.vo.FBoardVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 읽기</title>
</head>
<body style="text-align: center;">
	<h1 style="text-align: center;">게시글 읽기</h1>
	<hr>
	<%
	String id = request.getParameter("id");
	FBoardDAO dao = new FBoardDAO();
	FBoardVO vo = dao.searchBoardID(id);
	dao.cntUp(Integer.parseInt(id));
	%>

	<table>
		<tr>
			<th width="50" height="20">제목</th>
			<th width="100"><%=vo.getTitle()%></th>
		</tr>
		<tr>
			<td width="50" height="500">내용</td>
			<td width="600"><%=vo.getContent()%>
			</td>
		</tr>
	</table>
	<br>

	<%
	if (session.getAttribute("id") == null) {
	} else if (((String) session.getAttribute("id")).equals(vo.getWriter())|| session.getAttribute("id").equals("admin")) {
	%>
	<%@ include file="updateBtn.jspf"%>
	<%@ include file="deleteBtn.jspf"%>
	<%
	}
	%>
	
<div style="text-align: right;margin-right: 15%;">
		<button onclick="location.href='/bbs/fboard?page=0'">목록으로</button >
	</div>
</body>
<Style>
table {
	border: 1;
	width: 70%;
	margin: auto;
}

table, td, th {
	border-collapse: collapse;
	border: 1px solid black;
	text-align: center;
}
</Style>
</html>