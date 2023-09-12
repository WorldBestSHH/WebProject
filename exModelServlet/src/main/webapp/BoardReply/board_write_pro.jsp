<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	int nowpage=(int)request.getAttribute("page");
	int row=(int)request.getAttribute("row");
	if(row==1){
%>
<script>
		alert("글이 등록되었습니다");
		location.href="/BoardReply/board_list.do?page="+<%= nowpage %>;
</script>
<%
	}else{
%>
<script>
		alert("등록을 실패했습니다")
		history.back();
</script>	
<%
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>