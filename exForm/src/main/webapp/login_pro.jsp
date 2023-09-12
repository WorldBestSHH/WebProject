<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 	
	//자바코드입력
	//request:요청처리
	String userid=request.getParameter("userid");
	String pass=request.getParameter("userpw");
	
	out.print("아이디 : "+userid+"<br>");
	out.print("비번 : "+pass+"<br>");
%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	아이디 : <%= userid %><br>
	비번 : <%= pass %>
</body>
</html>