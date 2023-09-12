<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="model.member.*, java.util.*" %>
<%
	request.setCharacterEncoding("utf-8");
	MemberDAO dao=MemberDAO.getInstance();
	
	String search="", key="";
	List<MemberDTO> list=null;
	
	if(request.getParameter("key")!=null){
		key=request.getParameter("key");
		search=request.getParameter("search");
		list=dao.memberList(search, key);
	}else{
		list=dao.memberList();
	}
%>
<html>
<head>
<title>회원목록 보여주기</title>

<STYLE TYPE="text/css">
<!--
body { font-family: 돋움, Verdana; font-size: 9pt}
td   { font-family: 돋움, Verdana; font-size: 9pt; text-decoration: none; color: #000000} 
--->
</STYLE>
<script>
	function member_search(){
		if(member.key.value==""){
			alert("검색어를 입력하세요");
			member.key.focus();
			return;
		}
		member.submit();
	} 
</script>
</head>
<body>
<table width="550" border="1" cellspacing="0" cellpadding="2" bordercolorlight="#173E7C" bordercolordark="white">
  <tr>
    <td width=50 align=center>번호</td>
    <td width=50 align=center>ID</td>
    <td width=80 align=center>이름</td>
    <td width=100 align=center>전화번호</td>
    <td width=100 align=center>등록일자</td>
    <td width=100 align=center>최근접속일</td>
    
  </tr>
<% 
	int cnt=1;
	for(MemberDTO dto: list) {
%>
   <tr>
      <td align=center><%= cnt %></td>
      <td align=center><a href="userinfo_modify.jsp?userid=<%= dto.getUserid() %>"><%= dto.getUserid() %></a></td>
      <td align=center><%= dto.getName() %></td>
      <td align=center><%= dto.getTel() %></td>
      <td align=center><%= dto.getFirst_time() %></td>
      <td align=center><%= dto.getLast_time() %></td>
   </tr>
<%
		cnt++;
	} 
%>
</table>
<table width=550>
  <form name="member" method="post" action="userinfo_list.jsp">
  <tr>
    <td>
      <select name="search">
        <option value="name">이름 </option>
        <option value="userid">아이디 </option>
        <option value="tel">전화번호 </option>
        
    </td>
    <td>  검색:
          <input type="text" name="key" size=10> &nbsp;
          <a href="javascript:member_search()">[조회]</a>
     </td>
   </tr>
  <tr>
    <td>
    </td>
    <td></td>
   </tr>
  <tr>
    <td><a href="userlogin_form.jsp">로그인 페이지 이동</a>
    </td>
    <td><a href="userinfo_insert.jsp">회원가입페이지 이동</a></td>
   </tr>
   </form>
</table>    
</body>
</html>
