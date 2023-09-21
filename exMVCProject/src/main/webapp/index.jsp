<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>exMVC Project</h1>
	<h3><a href="/Guest?cmd=guest_list">방명록 바로가기</a></h3> <!-- servlet은 .jsp을 사용하지 않고 가상의 url을 만들 수 있음 -->
	<h3><a href="/Board?cmd=board_list.do">게시판 바로가기</a></h3>
	<h3><a href="/Member?cmd=userinfo_list.do">회원가입 바로가기</a></h3>
	<h3><a href="/Pds?cmd=pds_list.do">자료실 바로가기</a></h3>
	<h3><a href="/BoardReply?cmd=board_list.do">답변형 게시판 바로가기</a></h3>
	<h3><a href="/BoardEditor?cmd=board_list.do">게시판 에디터 바로가기</a></h3>
</body>
</html>