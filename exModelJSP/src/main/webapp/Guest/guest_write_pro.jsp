<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.guest.*" %>
<%
	GuestDAO dao=new GuestDAO();
	GuestDTO dto=new GuestDTO();
	
/*	String name=request.getParameter("name");
	dto.setName(name);
*/
	int nowpage=Integer.parseInt(request.getParameter("page"));
	request.setCharacterEncoding("utf-8");
	dto.setName(request.getParameter("name"));
	dto.setSubject(request.getParameter("subject"));
	dto.setContents(request.getParameter("contents"));
	
	int row=dao.guestWrite(dto);
	if(row==1){
		response.sendRedirect("guest_list.jsp?page="+nowpage); //response는 응답 request는 요청
	}else{
%>
<script>
		alert("오류");
		location.back();
</script>
<%
	}
%>
