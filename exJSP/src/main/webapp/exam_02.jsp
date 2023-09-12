<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- jsp를 이용한 1~10 정수 출력 -->    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border=1 width=300>
		<tr>
			<td>번호</td><td>합계</td>
		</tr>
	<%
		int sum=0;
		for(int i=1;i<=10;i++){
			if(i%2==1){
				sum+=i;
			
	%> <%-- 스트립트릿 --%>
		<tr>
			<td><%= i %><%-- 표현식 --%></td><td><%= sum %></td>
		</tr>
	<%	
			}
		} 
	%>
	</table>
</body>
</html>