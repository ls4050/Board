<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.dao.FBoardDAO"%>
<%@ page import="model.vo.FBoardVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Board</title>
</head>
<body>
	<%
	String id = request.getParameter("id");
	FBoardDAO dao = new FBoardDAO();
	FBoardVO vo = dao.searchBoardID(id);
	%>
	<%
	if (session.getAttribute("id") == null) {
		System.out.println("널입니다!");
	} else if (((String) session.getAttribute("id")).equals(vo.getWriter())||session.getAttribute("id").equals("admin")) {
	%>
	<form method="post" action="/bbs/fboard">
		<input type="hidden" name="action" value="update"> 
		<input type="hidden" name="bid" value=<%=vo.getId()%>>
		제목 : 
		<input type="text" value='<%=vo.getTitle()%>' required name="title">
		<br> 내용 : <br>
		<textarea rows=10 cols=35 name="content"><%=vo.getContent()%></textarea>
		<br> <input type="submit" value="수정"> <input type="reset" value="재작성">
	</form>
	<%
	}
	%>
</body>
</html>