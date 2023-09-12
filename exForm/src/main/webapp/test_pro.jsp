<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 웹 상에서는 숫자도 문자열 -->
<%
	request.setCharacterEncoding("utf-8");
	String gender=request.getParameter("gender");
	String fa[]=request.getParameterValues("fa"); //배열
	String job=request.getParameter("job");
	String intro=request.getParameter("intro");
	
	out.print("성별 : "+gender+"<br>");
	out.print("운동 : ");
	for(int i=0;i<fa.length;i++){
		if(i!=fa.length-1)
			out.print(fa[i]+",");
		else
			out.print(fa[i]);
	}
	out.print("<br>");
	out.print("직업 : "+job+"<br>");
	out.print("소개 : "+intro+"<br>");
%>