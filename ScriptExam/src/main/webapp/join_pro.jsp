<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String name=request.getParameter("name");
	String userid=request.getParameter("userid");
	String userpw=request.getParameter("userpw");
	String email1=request.getParameter("email1");
	String email2=request.getParameter("email2");
	String tel1=request.getParameter("tel1");
	String tel2=request.getParameter("tel2");
	String tel3=request.getParameter("tel3");
	String gender=request.getParameter("gender");
	String hobby[]=request.getParameterValues("hobby");
	String job=request.getParameter("job");
	
	out.print("이름 : "+name+"<br>");
	out.print("아이디 : "+userid+"<br>");
	out.print("비밀번호 : "+userpw+"<br>");
	out.print("이메일 : "+email1+"@");
	out.print(email2+"<br>");
	out.print("전화번호 : "+tel1+"-");
	out.print(tel2+"-");
	out.print(tel3+"<br>");
	out.print("성별 : "+gender+"<br>");
	out.print("취미 : ");
	for(int i=0;i<hobby.length;i++){
		if(i!=hobby.length-1)
			out.print(hobby[i]+",");
		else
			out.print(hobby[i]);
	}
	out.print("<br>");
	out.print("직업 : "+job);
%>