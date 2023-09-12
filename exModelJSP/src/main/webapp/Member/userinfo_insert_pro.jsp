<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.member.*" %>
<%
	MemberDAO dao=MemberDAO.getInstance();
	MemberDTO dto=new MemberDTO();
	
	request.setCharacterEncoding("utf-8");
	dto.setName(request.getParameter("name"));
	dto.setUserid(request.getParameter("userid"));
	dto.setPasswd(request.getParameter("passwd"));
	dto.setGubun(request.getParameter("gubun"));
	dto.setZip(request.getParameter("zip"));
	dto.setAddr1(request.getParameter("addr1"));
	dto.setAddr2(request.getParameter("addr2"));
	dto.setTel(request.getParameter("tel"));
	dto.setEmail(request.getParameter("email"));
	dto.setIntro(request.getParameter("intro"));
	
	String job=request.getParameter("job");
	String fa[]=request.getParameterValues("fa");
	
	if(job.equals("0")){
		job="";
	}
	dto.setJob(job);
	
	String favorite="";
	if(fa!=null){
		favorite=fa[0];
		for(int i=1;i<fa.length;i++){
			favorite=favorite+","+fa[i];
		}
	}
	dto.setFavorite(favorite);
	
	int row=dao.memberInsert(dto);
	if(row==1){
%>
	<script>
		alert("등록되었습니다.");
		location.replace("userinfo_list.jsp");
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