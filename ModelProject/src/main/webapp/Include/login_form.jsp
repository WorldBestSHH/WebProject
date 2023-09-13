<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Login</title>
<script>
	function leftcheck() {
		if (leftlogin.userid.value == "") {
			alert("아이디입력");
			leftlogin.userid.focus();
			return false;
		}
		if (leftlogin.passwd.value == "") {
			alert("비번입력");
			leftlogin.passwd.focus();
			return false;
		}
	}

	function autoSubmit() {
		if (event.keyCode == 13)
			leftcheck();
	}
</script>
</head>

<body topmargin=0>
	<c:if test="${empty user }">
		<table width="100%" height="120" border="0">
			<form name="leftlogin" action="/Member/userinfo_login.do" method="post">
				<tr>
					<td colspan="2" bgcolor="#6FA0E" height="20" align="center"><font
						size="2" color="white"><b>Member Login</b></font></td>
				</tr>
				<tr>
					<td><font size="2">아 이 디</font></td>
					<td><input type="text" size="10" name="userid"></td>
				</tr>
				<tr>
					<td><font size="2">비밀번호</font></td>
					<td><input type="password" size="10" name="passwd"></td>
				</tr>
				<tr>
					<td><input type="image" src="/Include/img/login1.gif" border="0" onClick="return leftcheck()"></td>
					<td><a href="userinfo_insert.do">
						<img src="/Include/img/regist.gif" border="0"></a></td>
				</tr>
			</form>
		</table>
	</c:if>
	<c:if test="${!empty user }">
		<table width="100%" height="120" border="0">
			<tr>
				<td bgcolor="#6FA0E" align="center" height="20"><font size="2" color="white">${user.name} 님!</font></td>
			</tr>
			<tr>
				<td align="center"><font size="2"> <a href="logout.do">[ 로그아웃 ] </a><br>
					<br> <a href="userinfo_modify.do">[ 정보수정 ]</a><br>
				</font></td>
			</tr>
		</table>
	</c:if>
</body>
</html>
