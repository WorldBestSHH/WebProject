<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="model.pds.*, java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>자료실 리스트 보기</title>
<link rel="stylesheet" type="text/css" href="stylesheet.css">
<style type="text/css">
	a.list {
		text-decoration: none;
		color: black;
		font-size: 10pt;
	}
</style>
<script>
	function pds_search() {
		if (!pds.key.value) {
			alert("검색어를 입력하세요");
			pds.key.focus();
			return;
		}
		pds.submit();
	}
</script>
</head>

<!-- 제목 부분 출력 -->
<body bgcolor="#FFFFFF" topmargin="0" leftmargin="0">
	<table border="0" width="800">
		<tr>
			<td width="20%" height="500" valign="top" bgcolor="#ecf1ef">
				<!--  다음에 추가할 부분 -->
			</td>
			<td width="80%" valign="top"><br>
				<table border="0" cellspacing="1" width="100%" align="center">
					<tr>
						<td colspan="7" align="center" valign="center" height="20">
							<font size="4" face="돋움" color="blue"> 
							<img src="./img/bullet-01.gif"> <b>참 좋은 자료들</b></font></td>
					</tr>
					<tr>
						<td colspan="7" align="right" valign="middle" height="20">
							<font size="2" face="고딕">전체 : <b>${totcount}</b>건
						</font></td>
					</tr>
					<tr bgcolor="e3e9ff">
						<td width="7%" align="center" height="20"><font face="돋움" size="2">번호</font></td>
						<td width="32%" align="center" height="20"><font face="돋움" size="2">제목</font></td>
						<td width="35%" align="center" height="20"><font face="돋움" size="2">파일이름 및 크기</font></td>
						<td width="10%" align="center" height="20"><font face="돋움" size="2">올린이</font></td>
						<td width="11%" align="center" height="20"><font face="돋움" size="2">날짜</font></td>
						<td width="5%" align="center" height="20"><font face="돋움" size="2">조회</font></td>
					</tr>
					<c:forEach var="dto" items="${list}">
						<tr onMouseOver="style.backgroundColor='#D1EEEE'" onMouseOut="style.backgroundColor=''">
							<td align="center" height="25"><font face="돋움" size="2" color="#000000">${listcount}</font></td>
							<td align="left" height="20">&nbsp;<font face="돋움" size="2">
								<a href="/Pds/pds_view.do?idx=${dto.idx}&page=${page}">${dto.subject}</a></font></td>
							<c:if test="${!empty(dto.filename)}">
								<td align="center" height="20"><font face="돋움" size="2">${dto.filename}</td>
							</c:if>
							<c:if test="${empty(dto.filename)}">
								<td align="center" height="20"><font face="돋움" size="2"></td>
							</c:if>
							<td align="left" height="20"><font face="돋움" size="2">${dto.name}</font></td>
							<td align="left" height="20"><font face="돋움" size="2">${dto.regdate.substring(0,10)}</font></td>
							<td align="center" height="20"><font face="돋움" size="2">${dto.readcnt}</font></td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="7"><hr width="100%"></td>
					</tr>
					<tr>
						<td colspan="5" align="center">
							<font face="돋움" size="2" color="#000000">${pageSkip}</font></td>
					</tr>
					<tr>
						<td colspan="7" align="right">
							<a href="/Pds/pds_write.do?page=1">
								<img src="./img/write.gif" alt="자료등록" align="middle" border="0"></a> &nbsp;</td>
					</tr>

					<table border="0" cellspacing="0" width="100%">
						<tr>
							<td><center>
									<font color="#004080" size="4" face="Courier New"><strong>Search&nbsp;</strong></font>
									<form name="pds" method="post" action="/Pds/pds_list.do">
										<select name="search" size="1" style="font-family: 돋움체">
											<option value="subject">글제목</option>
											<option value="name">작성자</option>
											<option value="contents">글내용</option>
										</select> &nbsp;&nbsp;
										<input type="text" name="key" value="" size="20">&nbsp;&nbsp;
										<a href="javascript:pds_search()"><input type="image" src="./img/search2.gif" align="middle"></a></td>
						</tr>
						</form>
					</table>
				</table></td>
		</tr>
</body>