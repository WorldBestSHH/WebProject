<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="model.member.*" %>
<%
	MemberDTO dto=(MemberDTO)session.getAttribute("user");
%>
<html>
<head>
<title>회원등록</title>
<STYLE TYPE="text/css">
<!--
	body {
		font-family: 돋움, Verdana;
		font-size: 9pt
	}
	
	td {
		font-family: 돋움, Verdana;
		font-size: 9pt;
		text-decoration: none;
		color: #000000;
		BACKGROUND-POSITION: left top;
		BACKGROUND-REPEAT: no-repeat;
	}

-->
	.formbox {
		BACKGROUND-COLOR: #F0F0F0;
		FONT-FAMILY: "Verdana", "Arial", "Helvetica", "돋움";
		FONT-SIZE: 9pt
	}
---
>
</STYLE>
<script>
	function post_check() {
		window.open("post_check.jsp", "post_check", "x=150,y=200,width=350,height=230");
	}
	function send() {
		if (!member.passwd.value) {
			alert("비밀번호를 입력하세요");
			member.passwd.focus();
			return;
		}
		if (!member.repasswd.value) {
			alert("비밀번호를 확인하세요");
			member.repasswd.focus();
			return;
		}
		if (member.passwd.value != member.repasswd.value) {
			alert("비밀번호가 맞지 않습니다");
			member.repasswd.focus();
			return;
		}
		if (!member.tel.value) {
			alert("전화번호를 입력하세요");
			member.tel.focus();
			return;
		}
		member.submit();
	}

	function del() {
		member.reset();
	}
</script>	
</head>
<body bgcolor="#FFFFFF" LEFTMARGIN=0 TOPMARGIN=0>
	<%@ include file="/Include/topmenu.jsp"%>
	<table border="0" width="800">
		<tr>
			<td width="20%" bgcolor="#ecf1ef" valign="top" style="padding-left: 0;">
				<%@ include file="/Include/login_form.jsp"%>
			</td>
			<td width="80%" valign="top">&nbsp;<img src="/User/img/title1.gif"><br>
				<form name=member method=post action="/Member/userinfo_modify.do">
					<table border=0 cellpadding=0 cellspacing=0 border=0 width=730 valign=top>
						<tr>
							<td align=center><br>
								<table cellpadding=0 cellspacing=0 border=0 width=650 align=center>
									<tr>
										<td bgcolor="#7AAAD5">
											<table cellpadding=0 cellspacing=0 border=0 width=100%>
												<tr bgcolor=#7AAAD5>
													<td align=left BORDER="0" HSPACE="0" VSPACE="0"><img src="/User/img/u_b02.gif"></td>
													<td align=center bgcolor="#7AAAD5">
														<FONT COLOR="#FFFFFF"><b>사용자등록&nbsp;</b>
															<font color=black>(</font>
															<font color=red>&nbsp;*&nbsp;</font>
															<font color=black>표시항목은 반드시 입력하십시요.)</font>
														</FONT>
													</td>
													<td align=right BORDER="0" HSPACE="0" VSPACE="0">
														<img src="/User/img/u_b03.gif"></td>
												</tr>
											</table>
											<table cellpadding=3 cellspacing=1 border=0 width=100%>
												<tr>
													<td width=110 bgcolor=#EFF4F8>&nbsp;회원 성명<font color=red>&nbsp;*</font></td>
													<TD BGCOLOR=WHITE>
														<input type=text name=name size=16 maxlength=20 value="<%= dto.getName() %>" readonly>성명은 빈칸없이 입력하세요.</td>
												</tr>
												<tr>
													<TD BGCOLOR="#EFF4F8">&nbsp;회원 ID<font color=red>&nbsp;*</font></td>
													<TD BGCOLOR=WHITE>
														<table cellspacing=0 cellpadding=0>
															<tr>
																<td align=absmiddle>
																	<input type=text name=userid size=12 maxlength=16 value="<%= dto.getUserid() %>" style="width: 120" readonly>
																</td>
																<td><img src="/User/img/u_bt01.gif" hspace=2 border=0 name=img1 align=absmiddle></td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<TD BGCOLOR="#EFF4F8">&nbsp;비밀번호<font color=red>&nbsp;*</font></td>
													<TD BGCOLOR=WHITE>
														<input type=password name=passwd size=8 maxlength=12 style="width: 80"> 영문이나 숫자만 가능합니다.</td>
												</tr>
												<tr>
													<TD BGCOLOR="#EFF4F8">&nbsp;전화번호<font color=red>&nbsp;*</font></td>
													<TD BGCOLOR=WHITE>
														<input type=text name=tel size=13 maxlength=13 value="<%= dto.getTel() %>"></td>
												</tr>
												<tr>
													<TD BGCOLOR="#EFF4F8">&nbsp;E-mail <font color=red>&nbsp;</font>
													</td>
													<td bgcolor=WHITE valign=middle>
														<% if(dto.getEmail()!=null) { %>
															<input type=text name=email size=30 maxlength=30 value="<%= dto.getEmail() %>">
															<input type="button" value="인증하기"></td>
														<% }else { %>
															<input type=text name=email size=30 maxlength=30 value="">
															<input type="button" value="인증하기"></td>
														<% } %>
												</tr>
											</table>
											<table cellpadding=0 cellspacing=0 border=0 width=100%>
												<tr bgcolor=#7AAAD5>
													<td valign=bottom><img src="/User/img/u_b04.gif" align=left hspace=0 vspace=0 border=0></td>
													<td align=center></td>
													<td valign=bottom><img src="/User/img/u_b05.gif" align=right hspace=0 vspace=0 border=0></td>
												</tr>
												<tr bgcolor=#ffffff>
													<td colspan=3 align=center><img src="/User/img/u_bt06.gif" vspace=3 border=0 name=img3> 
														<img src="/User/img/u_bt05.gif" border=0 hspace=10 vspace=3 name=img4></td>
												</tr>
											</table>
										</td>
									</tr>
									</td>
									</tr>
								</table>
								</form></td>
						</tr>
					</table>
					<%@ include file="/Include/copyright.jsp"%>
</body>
</html>
