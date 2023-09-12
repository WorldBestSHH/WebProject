<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	<%-- 스크립트가 가장 먼저 작동함 
	
		 변수를 선언할 때 동적으로 메모리가 할당됨
		 => int, String을 선언하지 않아도 됨. var는 사용해도 되고 안해도 되고 --%>
	function send(){
		if(document.login.userid.value==""){ //document는 생략 가능
			alert("아이디를 입력하세요")
			document.login.userid.focus();
			return false; //onSubmit="return send(); 가 true일 때 동작되므로
		}
		if(document.login.userid.value.length<6){ 
			alert("아이디는 6자이상 입력하세요")
			document.login.userid.focus();
		return false; 
	}
		if(!document.login.passwd.value){
			alert("비밀번호를 입력하세요")
			document.login.passwd.focus();
			return false;
		}
		
		login.submit(); //form 전송
	}
</script>
</head>
<body>
	<form name="login" method="post" onSubmit="return send();" action="login_pro.jsp">
		<table width="300" border=1>
			<tr>
				<td colspan="2">로그인 폼-2</td>
			</tr>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="userid"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="passwd"></td>
			</tr>		
			<tr>
				<td colspan="2">
					<input type="submit" value="로그인">
					<input type="reset" value="취소"> 
					<!-- <input type="button" value="등록" onClick="send()">
						 <input type="button" value="취소"> -->
				</td>
			</tr>
		</table>
	</form>
</body>
</html>