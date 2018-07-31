<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String userId = (String)session.getAttribute("userId");
%>   
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>hello</title>
</head>
<body>
<% if(userId != null && userId.equals("admin")){ %>
	<%@ include file="adminHeader.jsp" %>
<% }else{ %>
	<%@ include file="header.jsp" %>
<% } %>
<hr>
<section>
	본문 영역
</section>
<hr>
<%@ include file="footer.html" %>


</body>
</html>



