<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	//자바코드 입력
	int idx=Integer.parseInt(request.getParameter("idx"));
	int tc=Integer.parseInt(request.getParameter("tc"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>환영합니다</h1>
	<h3>IDX : <%= idx %></h3> <%-- 변수출력 --%>
	<h3>TC : <%= tc %></h3> 
</body>
</html>