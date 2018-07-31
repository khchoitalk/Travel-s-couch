<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, notice.model.vo.Notice" %>    
<%
	ArrayList<Notice> list = 
		(ArrayList<Notice>)request.getAttribute(
				"noticeList");
	String userId = (String)session.getAttribute("userId");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>noticeListView</title>
<script type="text/javascript">
	function moveWritePage(){
		//글등록 버튼 클릭하면, noticeWriteForm.jsp
		// 파일로 페이지 이동시킴
		location.href = "/second/views/notice/noticeWriteForm.jsp";
	}
</script>
</head>
<body>
<% if(userId != null && userId.equals("admin")){ %>
	<%@ include file="../../adminHeader.jsp" %>
<% } else { %>
	<%@ include file="../../header.jsp" %>
<% } %>
<hr>
<h2 align="center">공지사항 전체 보기</h2>
<center>
<div style="width:600px;">
	<form action="/second/nsearch" method="post">
	<fieldset>
		<legend>제목 검색</legend> 
		<input type="search" name="keyword" size="50">
		&nbsp; &nbsp;
		<input type="submit" value="검색">
	</fieldset>
	</form>
</div>
</center>
<br>
<% if(userId != null && userId.equals("admin")){ %>
	<center>
	<button id="writeBtn" onclick="moveWritePage();">
	공지글 등록</button>
	</center>
<% } %>
<br>
<center>
	<a href="/second/nlist">목록전체보기</a>
</center>
<br><br>
<table align="center" width="650" cellspacing="0" 
border="1">
<tr>
	<th width="50">번호</th>
	<th width="400">제목</th>
	<th width="50">파일</th>
	<th width="150">날짜</th>
</tr>
<% for(Notice n : list){ %>
<tr>
	<td align="center"><%= n.getNoticeNo() %></td>
	<td align="left">
		<a href="/second/ndetail?no=<%= n.getNoticeNo() %>">
		<%= n.getNoticeTitle() %></a>
	</td>
	<td align="center">
	<% if(n.getOriginalFilepath() != null){ %>
		◎
	<% }else{ %>
		&nbsp;
	<% } %>
	</td>
	<td align="center"><%= n.getNoticeDate() %></td>
</tr>
<% } %>
</table>
</body>
</html>







