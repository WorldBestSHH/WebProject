<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="model.member.*" %>
<%
	MemberDAO dao=MemberDAO.getInstance();
	int count=0;
	String userid="";
	if(request.getParameter("userid")!=null){
		userid=request.getParameter("userid");
		count=dao.useridSelect(userid);
	}
%>
<HTML>
<HEAD>
<TITLE>사용자의 아이디를 체크합니다.</TITLE>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<STYLE TYPE="text/css">
<!--
body { font-family: 돋움, Verdana; font-size: 9pt}
td   { font-family: 돋움, Verdana; font-size: 9pt; text-decoration: none; color: #000000} 
--->
</STYLE>
<script>
	function id_check(){
		if(idcheck.userid.value==""){
			alert("ID를 입력하세요");
			idcheck.userid.focus();
			return;
		}
		idcheck.submit();
	}
	
	function win_close(count){
		if(count==0){
			opener.member.userid.value='<%= userid %>';
		}
		self.close();
	}
</script>
</HEAD>
<BODY bgcolor="#FFFFFF">
<TABLE CELLPADDING=0 CELLSPACING=0 BORDER=0 WIDTH=330>
  <TR BGCOLOR=#7AAAD5>
    <td align=left><img src="./img/u_b02.gif"></td>
    <td align=center><FONT COLOR="#FFFFFF"><b>아이디 중복 체크</FONT></td>
    <td align=right><img src="./img/u_b03.gif"></td>
  </tr>
</table>
<TABLE CELLPADDING=0 CELLSPACING=0 BORDER=0 WIDTH=330>
<TR BGCOLOR=#948DCF>
  <TD>
    <TABLE CELLPADDING=4 CELLSPACING=1 BORDER=0 WIDTH=330>
  	  <TR BGCOLOR="#FFFFFF">
        <TD ALIGN="center">
<%
	if(!userid.equals("")){
		if(count==0){
%>
          <br><FONT FACE="굴림"><B>사용 가능합니다.</B></FONT><br>
         <BR><FONT COLOR="#483cae"><b><%= userid %></b></FONT>는 아직 사용되지 않은 아이디입니다.
         <BR><FONT COLOR="#483cae"><b><%= userid %></b></FONT>로 등록하셔도 됩니다.
<%
		}else{
%>        
         <br><font face="굴림"><b>죄송합니다</b></font><br>
    	<BR><FONT COLOR="#483cae"><b><%= userid %></b></FONT>는 이미 사용 중인 아이디입니다<br>
    	다른 아이디를 사용하여 주십시오.
<%
		}
	}
%>
        <form name="idcheck" method="post" action="id_check.jsp">
    	<br>아이디를 입력하세요.<br> 
           <INPUT NAME=userid type=text size=16 maxlength=16>
      	   <input type=image src="./img/u_bt08.gif" border=0 vspace=0 onClick="id_check()">
		</form>
        </TD>
      </TR>
    </TABLE>
 </TD>
</TR>
</TABLE>

<TABLE CELLPADDING=0 CELLSPACING=0 BORDER=0 WIDTH=330>
  <TR BGCOLOR=#7AAAD5>
    <td align=left><img src="./img/u_b04.gif"></td>
    <td align=right><img src="./img/u_b05.gif"></td>
  </tr>
  <tr>
    <td colspan=3 align=center>
      <img src="./img/u_bt13.gif" border=0 vspace=5 onClick="win_close('<%= count %>')">
    </td>
  </tr>
</table>
</BODY>
</HTML>