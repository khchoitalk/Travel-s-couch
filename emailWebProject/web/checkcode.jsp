<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- 비교하면 찾아짐 -->
<!-- 두번째 비교해보세요 -->
<html>
<head>
<meta charset="UTF-8">
<title>인증번호 확인 페이지</title>

<script type = "text/javascript">
	function checkCode() {
		var v1 = form2.code_check.value;
		var v2 = form2.code.value;
		if(v1!=v2){
			document.getElementById('checkCode').style.color = "red";
			document.getElementById('checkCode').innerHTML = "잘못된 인증번호";
			makeNull();
		}else {
			document.getElementById('checkCode').style.color = "blue";
			document.getElementById('checkCode').innerHTML = "인증되었습니다";
			makeReal();
		}
	}
	function makeReal(){
		var h1 = document.getElementById("h1");
		h1.type = "submit";
	}
	function makNull(){
		var h1 = document.getElementById("h1");
		h1.type = "hidden";
	}
</script>

</head>
<body>
<form id = "form2" action = "javascript:getPassword()">
	<table>
		<tr>
			<td><span>인증번호</span></td>
			<td><input type = "text" name = "code" id = "code"
				onkeyup = "checkCode()" placeholder = "인증번호를 입력하세요" />
				<div id = "checkCode"></div></td>
			<td><input type = "hidden" readonly = "readonly" name = "code_check"
				id = "code_check" value = "<%=request.getAttribute("code")%>" /></td>
		</tr>
	</table>
	<input id = "h1" type = "hidden" value = '인증하기' />
</form>


</body>
</html>