<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String name=request.getParameter("name");
	String userid=request.getParameter("userid");
	String passwd=request.getParameter("passwd");
	String repasswd=request.getParameter("repasswd");
	String gubun=request.getParameter("gubun");
	String zip=request.getParameter("zip");
	String addr1=request.getParameter("addr1");
	String addr2=request.getParameter("addr2");
	String tel=request.getParameter("tel");
	String email=request.getParameter("email");
	String fa[]=request.getParameterValues("fa");
	String job=request.getParameter("job");
	String intro=request.getParameter("intro");
	
	out.print("이름 : "+name+"<br>");
	out.print("아이디 : "+userid+"<br>");
	out.print("비밀번호 : "+passwd+"<br>");
	out.print("비번확인 : "+repasswd+"<br>");
	if(gubun!=null){
		out.print("주소구분 : "+gubun+"<br>");
	}
	if(zip!=""){
		out.print("우편번호 : "+zip+"<br>");
	}
	if(addr1!=""){
		out.print("주소 : "+addr1+"<br>");
	}
	if(addr2!=""){
		out.print("나머지주소 : "+addr2+"<br>");
	}
	out.print("전화번호 : "+tel+"<br>");
	out.print("이메일 : "+email+"<br>");
	if(fa!=null){
		out.print("관심분야 : ");
		for(int i=0;i<fa.length;i++){
			if(i!=fa.length-1)
				out.print(fa[i]+",");
			else
				out.print(fa[i]);
		}
	}
	if(!job.equals("0")){
		out.print("직업 : "+job+"<br>");
	}
	if(intro!=""){
		out.print("자기소개 : "+intro+"<br>");
	}
%>
<%-- 이름 name(text) *
	 회원Id userid(text) *
	 비밀번호 passwd(password) *
	 비번확인 repasswd(password) *
	 주소구분 gubun(radio)
	 우편번호 zip(text)
	 주소 addr1(text)
	 나머지 주소 addr2(text)
	 전화번호 tel(text) *
	 E-mail email(text) *
	 관심분야 fa(checkbox)
	 직업 job(select)
	 자기소개 intro(textarea) --%>