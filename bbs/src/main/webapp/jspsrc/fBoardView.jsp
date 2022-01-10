<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="model.dao.FBoardDAO, java.util.ArrayList, model.vo.FBoardVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유 게시판</title>
</head>
<body>
	<h1>POSCO FREE BOARD</h1>
	
	<%
	if (session.getAttribute("id") != null) {
	%>
	<h3><%= session.getAttribute("id")%> 님 어서오세요 !</h3>
	<div style="text-align: right; margin-right: 15%;" class="logout_btn">
		<form method="post" action="/bbs/jspsrc/logout.jsp">
			<input type=submit value="LOGOUT" class="btn">
		</form>
	</div>
	<div style="text-align: right; margin-right: 15%;">
		<form method="post" action="/bbs/jspsrc/makeBoard.jsp">
			<input type="submit" value="글 작성">
		</form>
	</div>
	<%
	} else {
	%>
	<div style="text-align: right; margin-right: 15%;">
		<form method="POST" action="/bbs/jspsrc/signIn.jsp">
			<button>LOGIN</button>
		</form>
	</div>
	<%
	}
	FBoardDAO fdao = new FBoardDAO();
	int count = fdao.boardNum();
	%>
	<h3>게시글 수 : <%= count%></h3>
	<table border="1">
		<tr>
			<th>게시물 번호</th>
			<th>게시물 제목</th>
			<th>작성자</th>
			<th>작성시간</th>
			<th>조회수</th>
		</tr>
		<%
		ArrayList<FBoardVO> list = (ArrayList) request.getAttribute("list");
		if (list != null) {
			for (FBoardVO vo : list) {
		%>

		<tr>
			<td class='<%=vo.getId()%>'><%=vo.getId()%></td>
			<td class='<%=vo.getId()%>'><a
            href="/bbs/jspsrc/readBoard.jsp?id=<%=vo.getId()%>"><%=vo.getTitle()%></a></td>
			<td class='<%=vo.getId()%>'><a href="/bbs/fboard?keyIn=<%=vo.getWriter()%>&action=search&comboBox=sWriter"><%=vo.getWriter()%></a></td>
			<td class='<%=vo.getId()%>'><%=vo.getWriteDate()%></td>
			<td class='<%=vo.getId()%>'><%=vo.getCnt()%></td>
		</tr>
		<%
		}
		%>
	</table>
	<%
		int pNum = count/10;
		int pRest = count%10;
		if(pRest==0) {
			pNum = pNum-1;
		}
	%>
	
	<div style="text-align:center">
	<% for(int i=0; i<=pNum; i++) {%>
			<a href="/bbs/fboard?page=<%=i%>">[ <%=i+1%> ]</a>
	<%} %>
	</div>
	
	
	<div style="text-align: center;">
		<form method="post" action="/bbs/fboard">
			<input type="hidden" name="action" value="search"> <select
				name="comboBox">
				<option value="sTitle" selected>제목</option>
				<option value="sContent">글내용</option>
				<option value="sWriter">작성자</option>
			</select> <input type="text" name="keyIn" required> <input
				type="submit" value="검색">
		</form>
	</div>
	<div style="text-align: right;margin-right: 15%;">
		<button onclick="location.href='/bbs/fboard?page=0'">목록으로</button >
	</div>	


	<%
	}
	if (request.getAttribute("msg") != null) {
	%>
	<script>
		alert('${ msg }');
		document.location.href = "/bbs/jspsrc/signIn.jsp";
	</script>

	<%
	}
	%>

	<%
	if (request.getAttribute("lmsg") != null) {
	%>
	<script>
		alert('${ lmsg }');
		document.location.href = "/bbs/fboard";
	</script>

	<%
	} else if (request.getAttribute("lfail") != null) {
	%>
	<script>
		alert('${ lfail }');
		document.location.href = "/bbs/jspsrc/signIn.jsp";
	</script>
	<%
	}
	%>

	<%
	if (request.getAttribute("umsg") != null) {
	%>
	<script>
		alert('${ umsg }');
		document.location.href = "/bbs/jspsrc/readBoard.jsp?id=" + ${bid};
	</script>

	<%
	}
	%>
	<%
	if (request.getAttribute("dmsg") != null) {
	%>
	<script>
		alert('${ dmsg }');
		document.location.href = "/bbs/fboard";
	</script>

	<%
	}
	%>
	<%
	if (request.getAttribute("imsg") != null) {
	%>

	<script>
		alert('${ imsg }');
		document.location.href = "/bbs/fboard";
	</script>

	<%
	}
	%>




</body>
<Style>
h1, h3 {
	text-align: center;
}

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