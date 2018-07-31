<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="boardError.jsp" %>
<%@ page import="board.model.vo.Board" %>
<%
	Board board = (Board)request.getAttribute("board");
	int currentPage = ((Integer)request.getAttribute("page")).intValue();
	String userId = (String)session.getAttribute("userId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardUpdateView</title>
</head>
<body>
<% if(userId != null && userId.equals("admin")){ %>
	<%@ include file="../../adminHeader.jsp" %>
<% }else{ %>
	<%@ include file="../../header.jsp" %>
<% } %>
<hr>
<h1 align="center"><%= board.getBoardNum() %>번 게시글 수정페이지</h1>
<br>
<% if(board.getBoardLevel() ==0){ %> <%-- 원글일 때 --%>
<form action="/second/boriginup" method="post" 
enctype="multipart/form-data">
<input type="hidden" name="bnum" value="<%= board.getBoardNum() %>">
<input type="hidden" name="ofile" value="<%= board.getBoardOriginalFileName() %>">
<input type="hidden" name="rfile" value="<%= board.getBoardRenameFileName() %>">
<input type="hidden" name="page" value="<%= currentPage %>">
<table align="center" width="500" border="1" 
cellpadding="10" cellspacing="0">
<tr>
	<td height="15" width="100">제목</td>
	<td><input type="text" name="btitle" 
	value="<%= board.getBoardTitle() %>"></td>
</tr>
<tr>
	<td height="15" width="100">작성자</td>
	<td><input type="text" name="bwriter" 
	value="<%= board.getBoardWriter() %>" readonly></td>
</tr>
<tr>
	<td>내용</td>
	<td>
	<textarea name="bcontent" cols="50" rows="7"><%= board.getBoardContent() %>
	</textarea></td>
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
	<input type="file" name="upfile">
	</td>
</tr>
<tr align="center" valign="middle">
	<td colspan="2">
	<input type="submit" value="수정하기"> &nbsp;
	<input type="reset" value="취소"> &nbsp;
	&nbsp; &nbsp;
	<a href="/second/blist?page=<%= currentPage %>">[목록]</a>
	</td>
</tr>
</table>
</form>
<% }else{ %> <%-- 댓글일 때 --%>
<form action="/second/breplyup" method="post">
<input type="hidden" name="bnum" value="<%= board.getBoardNum() %>">
<input type="hidden" name="page" value="<%= currentPage %>">
<table align="center" width="500" border="1" 
cellpadding="10" cellspacing="0">
<tr>
	<td height="15" width="100">제목</td>
	<td><input type="text" name="btitle" 
	value="<%= board.getBoardTitle() %>"></td>
</tr>
<tr>
	<td height="15" width="100">작성자</td>
	<td><input type="text" name="bwriter" 
	value="<%= board.getBoardWriter() %>" readonly></td>
</tr>
<tr>
	<td>내용</td>
	<td>
	<textarea name="bcontent" cols="50" rows="7"><%= board.getBoardContent() %>
	</textarea></td>
</tr>
<tr align="center" valign="middle">
	<td colspan="2">
	<input type="submit" value="수정하기"> &nbsp;
	<input type="reset" value="취소"> &nbsp;
	&nbsp; &nbsp;
	<a href="/second/blist?page=<%= currentPage %>">[목록]</a>
	</td>
</tr>
</table>
</form>
<% } %>
<br><br>
<hr>
<%@ include file="../../footer.html" %>
</body>
</html>