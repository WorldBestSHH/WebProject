<%@ page contentType="text/html; charset=UTF-8"%>

<html>
<head>
<title>게시판 내용 보기</title>
<link rel="stylesheet" type="text/css" href="/stylesheet.css">
<style type="text/css">
	td.title {
		padding: 4px;
		background-color: #e3e9ff
	}
	
	td.content {
		padding: 10px;
		line-height: 1.6em;
		text-align: justify;
	}
	
	a.list {
		text-decoration: none;
		color: black;
		font-size: 10pt;
	}
</style>
<script>
	function pds_delete() {
		url = "/Pds/pds_delete.do?idx=${dto.idx}&page=${page}";
		window.open(url, "pds_delete", "width=300, height=200");
	}

	//오류나서 x
	function pds_down(url) {
		var escapeURL = encodeURI(url, 'UTF-8');
		location.href = "/Pds/pds_down.do?filename=" + escapeURL;
	}
</script>
</head>

<!--DB에서 검색한 자료를 화면에 출력  -->
<body topmargin="0" leftmargin="0">
	<%@ include file="/Include/topmenu.jsp"%>
	<table border="0" width="800">
		<tr>
			<td width="20%" height="500" bgcolor="#ecf1ef" valign="top">
				<%@ include file="/Include/login_form.jsp"%>
			</td>
			<td width="80%" valign="top">&nbsp;<br>
				<table border="0" width="90%" align="center">
					<tr>
						<td colspan="2"><img src="./img/bullet-01.gif"> 
							<font color="blue" size="3">참 좋은 자료실</font><font size="2"> - 자료읽기</font></td>
					</tr>
				</table>
				<p>
				<table border="0" width="90%" align="center" cellspacing="0" style="border-width: 1px; border-color: #0066cc; border-style: outset;">
					<tr bgcolor="e3e9ff"> <td class="title"><img src="./img/bullet-04.gif"> 
						<font size="2" face="돋움">${dto.subject} </font></td>
					</tr>
					<tr>
						<td class="content">
							<p align="right">
								<font size="2" face="돋움"> ${dto.name} / ${dto.regdate} / ${dto.readcnt }번 읽음
									<p>${dto.contents}
									<p><img src="./img/disk.gif" align="middle" width="22" height="20" border="0">&nbsp; 
									<a href="upload/${dto.filename}">${dto.filename}</a>
								</font>
								<!-- <a href="javascript:pds_down('${pds.filename}')"> 오류나서 x -->
						</td>
					</tr>
				</table>
				<p align="center">
					<font size="2"> 
						<a href="/Pds/pds_modify.do?idx=${dto.idx}&page=${page}">
							<img src="./img/edit-1.gif" border="0"></a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="javascript:pds_delete()">
							<img src="./img/del.gif" border="0"></a>&nbsp;&nbsp;&nbsp;&nbsp; 
						<a href="/Pds/pds_list.do?page=${page}">
							<img src="./img/list-2.gif" border="0"></a>
					</font></td>
		</tr>
	</table>
	<%@ include file="/Include/copyright.jsp"%>
</body>
</html>


