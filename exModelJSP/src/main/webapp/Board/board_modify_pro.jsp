<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.board.*" %>
<%
	BoardDAO dao=BoardDAO.getInstance();
	BoardDTO dto=new BoardDTO();
	
	request.setCharacterEncoding("utf-8");
	int nowpage=Integer.parseInt(request.getParameter("page"));
	dto.setIdx(Integer.parseInt(request.getParameter("idx")));
	dto.setEmail(request.getParameter("email"));
	dto.setSubject(request.getParameter("subject"));
	dto.setContents(request.getParameter("contents"));
	
	int row=dao.boardModify(dto);
	if(row==1){
		response.sendRedirect("board_list.jsp?page="+nowpage);
	}else{
%>	
	<script>
		alert("잠시후 다시 수정하세요.");
		history.back();
	</script>
<%
	}
%>