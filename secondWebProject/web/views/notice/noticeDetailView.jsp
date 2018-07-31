<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="notice.model.vo.Notice" %>
<%
	Notice notice = (Notice)request.getAttribute("notice");
	String userId = (String)session.getAttribute("userId");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>noticeDetailView</title>
<script type="text/javascript">
	function showList(){
		location.href = "/second/nlist";
		return false;
	}
</script>
</head>
<body>
<% if(userId != null && userId.equals("admin")){ %>
	<%@ include file="../../adminHeader.jsp" %>
<% }else{ %>
	<%@ include file="../../header.jsp" %>
<% } %>
<hr>
<h2 align="center">
<%= notice.getNoticeNo() %> 번 공지사항</h2>
<br>
<form action="/second/nupdate" method="post" 
enctype="multipart/form-data">
<input type="hidden" name="no" value="<%= notice.getNoticeNo() %>">
<input type="hidden" name="rfile" value="<%= notice.getRenameFilepath() %>">
<table align="center" width="600" cellspacing="0" 
border="1">
<tr>
	<th>제목</th>
	<td><input type="text" name="ntitle" 
	value="<%= notice.getNoticeTitle() %>"></td>
</tr>
<tr>
	<th>작성자</th>
	<td><input type="text" name="nwriter" readonly 
	value="<%= notice.getNoticeWriter() %>"></td>
</tr>
<tr>
	<th>첨부파일</th>
	<td>
		<% if(notice.getOriginalFilepath() != null){ %>
			<a href="/second/fdown?ofile=<%= notice.getOriginalFilepath() %>&rfile=<%= notice.getRenameFilepath() %>">
			<%= notice.getOriginalFilepath() %></a>
		<% }else{ %>
			첨부파일 없음
		<% } %> <br>
		<input type="file" name="upfile">
	</td>
</tr>
<tr>
	<th>내용</th>
	<td>
	<textarea name="ncontent" cols="70" rows="10">
	<%= notice.getNoticeContent() %>	
	</textarea>
	</td>
</tr>
<tr>
<th colspan="2">
	<% if(userId != null && userId.equals("admin")){	%>
	 <input type="submit" value="수정하기"> &nbsp;
	 <a href="/second/ndelete?no=<%= notice.getNoticeNo() %>">삭제하기</a> &nbsp;
	<% }%>
	<button onclick="return showList();">목록</button>
	
</th>
</tr>
</table>
</form>

</body>
</html>







