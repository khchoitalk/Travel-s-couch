<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//jsp 파일 안에는 request, session 객체도 내장되어 있음
	String userName = (String)session.getAttribute("userName");
	String userid = (String)session.getAttribute("userId");
%>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header</title>
<style type="text/css">
	#menubar ul li {
		float: left;
		width: 120px;
		height: 35px;
		display: inline;
		background: orange;
	}
	
	#menubar
	
	hr { clear: both; }
	
	div#loginForm table {
		background : LightSkyBlue;
	}
</style>
</head>
<body>
<header>
<h1>second</h1>
<nav id="menubar">
<ul>
	<li><a href="/second/test">testServlet</a></li>
	<li><a href="/second/nlist">공지사항</a></li>
	<li><a href="/second/blist">게시판</a></li>
	<li><a>QnA(답글게시판)</a></li>
	<li><a>사진게시판</a></li>
</ul>
</nav>
<div id="loginForm">
<% if(userName == null){ %>
<form action="/second/login" method="post">
<table width="250" height="75" cellspacing="0" 
cellpadding="0">
<tr><td width="200">
	<input type="text" name="userid" id="userid" size="15">
	</td>
	<td width="50" rowspan="2">
	<input type="submit" value="로그인">
	</td></tr>
<tr><td>
	<input type="password" name="userpwd" size="15">
   </td></tr>
<tr><td colspan="2">
	<a href="views/member/memberEnroll.html">회원가입</a> &nbsp;
	<a href="#">아이디/암호조회</a>
</td></tr>
</table>
</form>
<% }else{ %>
<table width="250" height="75" cellspacing="0" 
cellpadding="0">
<tr><td width="150">
	<%= userName %> 님.
	</td>
	<td width="100">
	<a href="/second/logout">로그아웃</a>
	</td></tr>
<tr><td>	메일 개 </td> <td>쪽지  개</td></tr>
<tr><td colspan="2">
	<a href="/second/myinfo?userid=<%= userid %>">내 정보보기</a>	
	<%-- jsp 주석 태그임 
	a 태그의 href 속성이 연결할 대상 지정하는 속성임.
	href="서블릿url-pattern?쿼리스트링"
	쿼리스트링은 서블릿으로 전송할 값을 추가하는 부분임
	?이름=값&이름=<%= 변수 %>
	--%>
</td></tr>
</table>
<% } %>
</div>
</header>
</body>
</html>