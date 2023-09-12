<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.board.*" %>
<%
	BoardDAO dao=BoardDAO.getInstance();
	
	int nowpage=Integer.parseInt(request.getParameter("page"));
	int idx=Integer.parseInt(request.getParameter("idx"));
	int row=dao.boardDelete(idx);
	if(row==1){
%>
	<script>
		alert("삭제되었습니다.");
		window.opener.location.replace("board_list.jsp?page=<%= nowpage %>"); 
		self.close();
	</script>
<% 
	}else{
%>	
	<script>
		alert("비밀번호를 확인해주세요.");
		history.back();
	</script>
<%
	}
%>