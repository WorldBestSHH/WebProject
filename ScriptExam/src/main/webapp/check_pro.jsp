<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String gender=request.getParameter("gender");
	String sport[]=request.getParameterValues("sport");
	String job=request.getParameter("job");
	
	out.print("성별 : "+gender+"<br>");
	out.print("스포트 : ");
	for(int i=0;i<sport.length;i++){
		if(i!=sport.length-1){
			out.print(sport[i]+",");
		}else{
			out.print(sport[i]);
		}
	}
	out.print("<br>");
	out.print("직업 : "+job+"<br>");
%>
<%-- 
	 String str="";
	 if(sport!=null){
		for(int i=1;i<sport.length;i++){
			str=str+","+sport[i];
		}
	} 
	
	out.print(str);
--%>