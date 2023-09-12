<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	//메소드 정의
	int tot=0;
	int sum(int num){
		for(int i=1;i<=num;i++){
			tot+=i;
		}
		return tot;
	}
%>
<%
	//자바코드
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	함수호출 : 1~10 합 : <%= sum(10) %>
</body>
</html>