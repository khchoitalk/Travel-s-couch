<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<%  //scriptlet tag
	String message = (String)request.getAttribute("message");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>noticeError</title>
</head>
<body>
<h1>공지사항 서비스 오류 : </h1>
<% if(message != null){ %>
	<h3>예러 메세지 : <%= message %> </h3>
<% }else { %>
	<h3>예외 발생 : <%= exception.getMessage() %></h3>
<% } %>
<br>
<a href="/second/index.jsp">시작페이지로 이동</a>

</body>
</html>









