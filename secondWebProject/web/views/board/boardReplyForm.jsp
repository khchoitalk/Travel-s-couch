<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="boardError.jsp" %>
<%
	int boardNum = Integer.parseInt(request.getParameter("bnum"));
	int currentPage = Integer.parseInt(request.getParameter("page"));
	String userId = (String)session.getAttribute("userId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardReplyForm</title>
</head>
<body>
<% if(userId != null && userId.equals("admin")){ %>
	<%@ include file="../../adminHeader.jsp" %>
<% }else{ %>
	<%@ include file="../../header.jsp" %>
<% } %>
<hr>
<h1 align="center"><%= boardNum %>글의 댓글 달기</h1>
<br>
<form action="/second/breply" method="post">
<input type="hidden" name="bnum" value="<%= boardNum %>">
<input type="hidden" name="page" value="<%= currentPage %>">
<table align="center" width="500" border="1" 
cellpadding="10" cellspacing="0">
<tr>
	<td height="15" width="100">제목</td>
	<td><input type="text" name="btitle" size="50"></td>
</tr>
<tr>
	<td height="15" width="100">작성자</td>
	<td><input type="text" name="bwriter" 
	value="<%= userId %>" readonly></td>
</tr>
<tr>
	<td>내용</td>
	<td>
	<textarea name="bcontent" cols="50" rows="7">
	</textarea></td>
</tr>
<tr align="center" valign="middle">
	<td colspan="2">
	<input type="submit" value="댓글등록"> &nbsp;
	<input type="button" value="취소" 
	onclick="history.go(-1); return false;"> &nbsp;
	&nbsp; &nbsp;
	<a href="/second/blist?page=<%= currentPage %>">[목록]</a>
	</td>
</tr>
</table>
</form>
<br><br>
<hr>
<%@ include file="../../footer.html" %>
</body>
</html>



