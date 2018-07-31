<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String userId = (String)session.getAttribute("userId");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardWriteForm</title>
</head>
<body>
<% if(userId != null && userId.equals("admin")){ %>
	<%@ include file="../../adminHeader.jsp" %>
<% }else{ %>
	<%@ include file="../../header.jsp" %>
<% } %>
<hr>
<h1 align="center">게시글 원글 등록 페이지</h1>
<br>
<form action="/second/binsert" method="post" 
enctype="multipart/form-data">
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
<tr>
	<td>첨부파일</td>
	<td>
		<input type="file" name="upfile">
	</td>
</tr>
<tr align="center" valign="middle">
	<td colspan="2">
	<input type="submit" value="등록하기"> &nbsp;
	<input type="reset" value="취소"> &nbsp;
	&nbsp; &nbsp;
	<a href="/second/blist">[목록]</a>
	</td>
</tr>
</table>
</form>
<br><br>
<hr>
<%@ include file="../../footer.html" %>
</body>
</html>