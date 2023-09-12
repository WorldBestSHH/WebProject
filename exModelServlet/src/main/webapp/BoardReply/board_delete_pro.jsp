<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	int nowpage=(int)request.getAttribute("page");
	int row=(int)request.getAttribute("row");
	if(row==1){
%>
		<script>
			alert("삭제되었습니다");
			window.opener.location.replace("/BoardReply/board_list.do?page=${page}");
			self.close();
		</script>
<%
	}else if(row==-1){
%>		
		<script>
			alert("삭제를 실패하였습니다");
			window.opener.location.replace("/BoardReply/board_list.do");
			self.close();				
			</script>
<%
	}else{	
%>		
		<script>
			alert("비밀번호가 맞지 않습니다");
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