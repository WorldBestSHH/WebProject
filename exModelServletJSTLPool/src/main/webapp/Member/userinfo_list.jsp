<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="model.member.*, java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>회원목록 보여주기</title>

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
		color: #000000
	}
---
>
</STYLE>
</head>
<body>
	<table width="550" border="1" cellspacing="0" cellpadding="2"
		bordercolorlight="#173E7C" bordercolordark="white">
		<tr>
			<td width=50 align=center>번호</td>
			<td width=50 align=center>ID</td>
			<td width=80 align=center>이름</td>
			<td width=100 align=center>전화번호</td>
			<td width=100 align=center>등록일자</td>
			<td width=100 align=center>최근접속일</td>
		</tr>
		<c:set var="cnt" value="1" />
		<c:forEach var="dto" items="${list}">
			<tr>
				<td align=center><c:out value="${cnt}" /></td>
				<td align=center>${dto.userid}</td>
				<td align=center>${dto.name}</td>
				<td align=center>${dto.tel}</td>
				<td align=center>${dto.first_time}</td>
				<td align=center>${dto.last_time}</td>
			</tr>
			<c:set var="cnt" value="${cnt+1}" />
		</c:forEach>


	</table>
	<table width=550>
		<tr>
			<td><select name="search">
					<option value="name">이름</option>
					<option value="userid">아이디</option>
					<option value="tel">전화번호</option></td>
			<td>검색: <input type="text" name="key" size=10> &nbsp; 
				<a href="/Member/userinfo_list.do">[조회]</a>
			</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<%
			if (session.getAttribute("user") != null) {
			%>
			<td><a href="/Member/userinfo_logout.do">로그아웃</a></td>
			<td><a href="/Member/userinfo_modify.do">내 정보 수정</a></td>
			<%
			} else {
			%>
			<td><a href="/Member/userinfo_login.do">로그인</a></td>
			<td><a href="/Member/userinfo_insert.do">회원가입</a></td>
			<%
			}
			%>

		</tr>
	</table>
</body>
</html>
