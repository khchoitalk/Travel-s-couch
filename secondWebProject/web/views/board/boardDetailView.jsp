<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="boardError.jsp" %>
<%@ page import="board.model.vo.Board" %>
<%
	Board board = (Board)request.getAttribute("board");
	int currentPage = ((Integer)request.getAttribute("currentPage")).intValue();
	String userId = (String)session.getAttribute("userId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardDetailView</title>
</head>
<body>
<% if(userId != null && userId.equals("admin")){ %>
	<%@ include file="../../adminHeader.jsp" %>
<% }else{ %>
	<%@ include file="../../header.jsp" %>
<% } %>
<hr>
<h1 align="center">게시글 상세보기</h1>
<table align="center" width="500" border="1" 
cellpadding="10" cellspacing="0">
<tr align="center" valign="middle">
	<th colspan="2">
	<%= board.getBoardNum() %>번글 상세보기</th>
</tr>
<tr>
	<td height="15" width="100">제목</td>
	<td><%= board.getBoardTitle() %></td>
</tr>
<tr>
	<td height="15" width="100">작성자</td>
	<td><%= board.getBoardWriter() %></td>
</tr>
<tr>
	<td height="15" width="100">작성날짜</td>
	<td><%= board.getBoardDate() %></td>
</tr>
<tr>
	<td height="15" width="100">조회수</td>
	<td><%= board.getBoardReadCount() %></td>
</tr>
<tr>
	<td>내용</td>
	<td><%= board.getBoardContent() %></td>
</tr>
<tr>
	<td>첨부파일</td>
	<td>
	<% if(board.getBoardOriginalFileName() != null){ %>
		<a href="/second/bfdown?ofile=<%= board.getBoardOriginalFileName() %>&rfile=<%= board.getBoardRenameFileName() %>">
		<%= board.getBoardOriginalFileName() %></a>
	<% }else{ %>
		첨부파일 없음
	<% } %>
	</td>
</tr>
<tr align="center" valign="middle">
	<td colspan="2">
	<% if(userId.equals(board.getBoardWriter()) == true){ %>
		<a href="/second/bupview?bnum=<%= board.getBoardNum() %>&page=<%= currentPage %>"">[수정페이지로 이동]</a> &nbsp; 
		<a href="/second/bdelete?bnum=<%= board.getBoardNum() %>">[삭제]</a>
	<% }else{ %>
		<a href="/second/views/board/boardReplyForm.jsp?bnum=<%= board.getBoardNum() %>&page=<%= currentPage %>">[댓글달기]</a>
	<% } %>
	&nbsp; &nbsp;
	<a href="/second/blist?page=<%= currentPage %>">[목록]</a>
	</td>
</tr>
</table>
<br><br>
<hr>
<%@ include file="../../footer.html" %>
</body>
</html>









