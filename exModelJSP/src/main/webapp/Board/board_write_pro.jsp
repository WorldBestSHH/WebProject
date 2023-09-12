<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.board.*" %>
<%
	BoardDAO dao=BoardDAO.getInstance();
	BoardDTO dto=new BoardDTO();
	
	request.setCharacterEncoding("utf-8");
	int nowpage=Integer.parseInt(request.getParameter("page"));
	dto.setName(request.getParameter("name"));
	dto.setEmail(request.getParameter("email"));
	dto.setSubject(request.getParameter("subject"));
	dto.setContents(request.getParameter("contents"));
	dto.setPass(request.getParameter("pass"));
	
	int row=dao.boardWrite(dto);
	if(row==1){
%>
	<script>
		alert("등록되었습니다.");
		location.replace("board_list.jsp?page=<%= nowpage %>");
	</script>
<% 
	}else{
%>	
	<script>
		alert("잠시후 다시 등록하세요.");
		history.back();
	</script>
<%
	}
%>