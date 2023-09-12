<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String userid=request.getParameter("userid");
	String passwd=request.getParameter("passwd");
	//db연결부분
	if(userid.equals("admin")&&passwd.equals("1234")){
%>
		<script>
			alert("로그인되었습니다");
			//location.href="index.jsp";
			location.href="index.jsp?idx=3&tc=30"; //값을 넘겨줄 때
		</script>
<% 		
	}else{
%>		
		<script>
			alert("아이디 또는 비밀번호 오류");
			history.back();
		</script>
<%		
	}
%>
