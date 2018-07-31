<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%! public int getRandom() {
	int random = 0;
	random = (int)Math.floor((Math.random()*(99999-10000+1)))+10000;
	return random;
}%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>email</title>
</head>
<body>

<form action = "/email/send" method = "post" id = "forml">
	<table>
		<tr>
			<td>
			<input type = "text" id = "receiver" name = "receiver" 
			placeholder = "이메일을 입력하시오" /></td>
			<td>
			<input id = "submit" type = "submit" value = "인증번호발송"></td>
			<td>
			<input type = "hidden" readonly = "readonly" name = "code_check"
			id = "code_check" value = "<%= getRandom() %>" /></td>
		</tr>	
	</table>
</form>
<br> &nbsp; &nbsp;
<button> 등 록 </button> &nbsp;&nbsp;
<button> 취 소 </button>



</body>
</html>