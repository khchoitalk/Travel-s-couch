<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="memberError.jsp" %>
<%@ page import="member.model.vo.Member, java.util.*" %>    
<%
	Member member = (Member)request.getAttribute("member");

	/* member = null; */
	
	//취미 처리
	String[] hobbies = member.getHobby().split(",");
	String[] checked = new String[9];
	for(String s : hobbies){
		switch(s){
		case "game": checked[0] = "checked"; break;
		case "reading": checked[1] = "checked"; break;
		case "music": checked[2] = "checked"; break;
		case "climb": checked[3] = "checked"; break;
		case "sport": checked[4] = "checked"; break;
		case "art": checked[5] = "checked"; break;
		case "bike": checked[6] = "checked"; break;
		case "walk": checked[7] = "checked"; break;
		case "sleep": checked[8] = "checked"; break;
		}
	}
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내정보 보기</title>
<script type="text/javascript" 
src="/second/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		//암호 입력 상자에서 포커스가 사라질 때 암호확인 처리함
		$("#pwd2").blur(function(){
			console.log("포커스가 사라짐...");
			var pwd1 = $("#pwd1").val();
			var pwd2 = $("#pwd2").val();
			
			if(pwd1 != pwd2){
				alert("암호와 암호확인이 일치하지 않습니다.");
				$("#pwd1").select();
			}			
		});
	});
</script>
</head>
<body>
<%@ include file="../../header.jsp" %>
<h1 align="center"><%= member.getUserId() %> 님의 정보</h1>

<form action="/second/mupdate" method="post">
<table width="600" align="center" cellspacing="5" 
bgcolor="#3399ff">
<tr><td>아이디</td>
<td>
	<input type="text" name="userid" 
	value="<%= member.getUserId() %>" readonly>
</td></tr>
<tr><td>이 름</td>
<td>
	<input type="text" name="username" 
	value="<%= member.getUserName() %>" readonly>
</td></tr>
<tr><td>암 호</td>
<td>
	<input type="password" name="userpwd" id="pwd1"
	value="<%= member.getUserPwd() %>">
</td></tr>
<tr><td>암호확인</td>
<td>
	<input type="password" id="pwd2">
</td></tr>
<tr><td>성 별</td>
<td>
	<% if(member.getGender().equals("M")){ %>
	<input type="radio" name="gender" value="M" checked> 남 &nbsp;
	<input type="radio" name="gender" value="F"> 여
	<% }else{ %>
	<input type="radio" name="gender" value="M"> 남 &nbsp;
	<input type="radio" name="gender" value="F" checked> 여
	<% } %>
</td></tr>
<tr><td>나 이</td>
<td>
	<input type="number" name="age"
	value="<%= member.getAge() %>">
</td></tr>
<tr><td>이메일</td>
<td>
	<input type="email" name="email" 
	value="<%= member.getEmail() %>">
</td></tr>
<tr><td>전화번호</td>
<td>
	<input type="tel" name="phone" 
	value="<%= member.getPhone() %>">
</td></tr>
<tr><td>취 미</td>
<td>
	<table>
	<tr><td><input type="checkbox" name="hobby" value="game" <%= checked[0] %>> 게임</td>
	<td><input type="checkbox" name="hobby" value="reading" <%= checked[1] %>> 독서</td>
	<td><input type="checkbox" name="hobby" value="music" <%= checked[2] %>> 음악감상</td></tr>
	<tr><td><input type="checkbox" name="hobby" value="climb" <%= checked[3] %>> 등산</td>
	<td><input type="checkbox" name="hobby" value="sport" <%= checked[4] %>> 운동</td>
	<td><input type="checkbox" name="hobby" value="art" <%= checked[5] %>> 그림그리기</td>
	</tr>
	<tr><td><input type="checkbox" name="hobby" value="bike" <%= checked[6] %>> 자전거타기</td>
	<td><input type="checkbox" name="hobby" value="walk" <%= checked[7] %>> 산책</td>
	<td><input type="checkbox" name="hobby" value="sleep" <%= checked[8] %>> 잠자기</td></tr>
	</table>
</td></tr>
<tr><td>하고싶은말</td>
<td>
	<textarea name="etc" rows="3" cols="70"><%= member.getEtc() %>
	</textarea>
</td></tr>
<tr>
	<td colspan="2" align="center">
		<input type="submit" value="수정하기">
		&nbsp; &nbsp;
		<a href="/second/mdelete?userid=<%= member.getUserId() %>">탈퇴하기</a>
	</td>
</tr>
</table>

</form>
<hr>
<%@ include file="../../footer.html" %>
</body>
</html>