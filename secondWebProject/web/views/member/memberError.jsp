<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<!-- scriptlet 태그라고 함 : 일반 메소드 안에서의 코드를
	작성하는 영역이라고 생각하면 됨 -->
<%
	//이 페이지로 포워딩된 request 객체 안의 저장된 값
	//꺼내기
	//jsp 에는 내장된 객체들이 있음
	//request 객체도 내장된 객체임. 그냥 사용하면 됨.
	String message = (String)request.getAttribute("message");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>errorPage</title>
</head>
<body>
<h1>회원 서비스 에러 발생하였습니다.</h1>
<h3>에러 메세지 : <%= message %></h3>

</body>
</html>





