<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="./boardError.jsp" %>
<%@ page import="board.model.vo.Board, java.util.ArrayList" %>
<%
	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
	int listCount = ((Integer)request.getAttribute("listCount")).intValue();
	int startPage = ((Integer)request.getAttribute("startPage")).intValue();
	int endPage = ((Integer)request.getAttribute("endPage")).intValue();
	int maxPage = ((Integer)request.getAttribute("maxPage")).intValue();
	int currentPage = ((Integer)request.getAttribute("currentPage")).intValue();
	
	String userId = (String)session.getAttribute("userId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardListView</title>
<script type="text/javascript">
	function showBoardWriteForm(){
		location.href = "/second/views/board/boardWriteForm.jsp";
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
<h2 align="center">게시글 목록</h2>
<h4 align="center">총 게시글 수 : <%= listCount %></h4>
<% if(userId != null){ %>
	<div style="align:center; text-align:center;">
	<button onclick="showBoardWriteForm();">글쓰기</button>
	</div>
<% } %>
<br>
<table align="center" border="1" cellspacing="0" 
width="700">
<tr>
	<th>번호</th><th>제목</th><th>작성자</th>
	<th>날짜</th><th>조회수</th><th>첨부파일</th>
</tr>
<% for(Board b : list){ %>
<tr>
	<td align="center"><%= b.getBoardNum() %></td>
	<td align="center">
	<%-- 댓글일 때는 제목을 들여쓰기함 --%>
	<% if(b.getBoardLevel() == 1){ //원글의 댓글일 때 %>
	&nbsp; &nbsp; ▶
	<% }else if(b.getBoardLevel() == 2){ //댓글의 댓글일 때 %>
	&nbsp; &nbsp; &nbsp; &nbsp; ▶▶
	<% } %> <%-- 들여쓰기 처리 --%>
	<%-- 로그인한 사용자만 상세보기할 수 있도록 처리 --%>
	<% if(userId != null){ %>
		<a href="/second/bdetail?bnum=<%= b.getBoardNum() %>&page=<%= currentPage %>"><%= b.getBoardTitle() %></a>
	<% }else{ %>
		<%= b.getBoardTitle() %>
	<% } %>
	</td>
	<td align="center"><%= b.getBoardWriter() %></td>
	<td align="center"><%= b.getBoardDate() %></td>
	<td align="center"><%= b.getBoardReadCount() %></td>
	<td align="center">
	<% if(b.getBoardOriginalFileName() != null){ %>
		◎
	<% }else{ %>
		&nbsp;
	<% } %>
	</td>
</tr>
<% } %>
</table>
<!-- 페이징 처리 -->
<div style="text-align: center">
<% if(currentPage <= 1){ %>
	[맨처음]&nbsp;
<% }else{ %>
	<a href="/second/blist?page=1">[맨처음]</a>
<% } %>
<% if((currentPage - 10) < startPage && 
		(currentPage - 10) > 1){ %>
	<a href="/second/blist?page=<%= startPage - 10 %>">[이전]</a>
<% }else{ %>
	[이전]&nbsp;
<% } %>
<%-- startPage ~ endPage 출력 --%>
<% for(int p = startPage; p <= endPage; p++){ 
		if(p == currentPage){ 
%>
	<font color="red" size="4">[<%= p %>]</font>
<%      }else{ %>
	<a href="/second/blist?page=<%= p %>"><%= p %></a>
<% }} %>
<%-- ---------------- --%>
<% if((currentPage + 10) > endPage && 
		(currentPage + 10) < maxPage){ %>
	<a href="/second/blist?page=<%= endPage + 10 %>">[다음]</a>
<% }else{ %>
	[다음]&nbsp;
<% } %>

<% if(currentPage >= maxPage){ %>
	[맨끝]&nbsp;
<% }else{ %>
	<a href="/second/blist?page=<%= maxPage %>">
	[맨끝]</a>
<% } %>
</div>
</body>
</html>







