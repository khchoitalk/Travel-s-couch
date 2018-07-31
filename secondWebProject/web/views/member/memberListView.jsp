<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, member.model.vo.Member" %> 
<%
	ArrayList<Member> list = 
		(ArrayList<Member>)request.getAttribute("list");
%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberListView</title>
</head>
<body>
<%@ include file="../../adminHeader.jsp" %>
<hr>
<h1 align="center">회원 전체 리스트 페이지</h1>
<br>
<table align="center" cellspacing="0" border="1">
<tr>
	<th>아이디</th>
	<th>암호</th>
	<th>이름</th>
	<th>성별</th>
	<th>나이</th>
	<th>전화번호</th>
	<th>이메일</th>
	<th>취미</th>
	<th>하고싶은말</th>
	<th>가입날짜</th>
	<th>마지막수정날짜</th>
	<th>로그인제한</th>
</tr>
<% for(Member m : list){ %>
<tr>
	<td><%= m.getUserId() %></td>
	<td><%= m.getUserPwd() %></td>
	<td><%= m.getUserName() %></td>
	<td><%= m.getGender() %></td>
	<td><%= m.getAge() %></td>
	<td><%= m.getPhone() %></td>
	<td><%= m.getEmail() %></td>
	<td><%= m.getHobby() %></td>
	<td><%= m.getEtc() %></td>
	<td><%= m.getEnrollDate() %></td>
	<td><%= m.getLastModifiedDate() %></td>
	<td><input type="checkbox" name="check"></td>
</tr>
<% } %>
<tr><th colspan="12">
	<button id="loginStop">로그인제한실행</button>
	&nbsp;
	<button id="loginRun">로그인허용실행</button>
</th></tr>
</table>

</body>
</html>








