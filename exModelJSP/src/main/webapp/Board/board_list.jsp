<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="model.board.*, model.util.*, java.util.*" %>
<%
	request.setCharacterEncoding("utf-8");
	BoardDAO dao=BoardDAO.getInstance();

	String search="", key="";
	String url="board_list.jsp", addtag="";
	int totcount=0;
	List<BoardDTO> list=null;
	
	if(request.getParameter("key")!=null){
		key=request.getParameter("key");
		search=request.getParameter("search");
		totcount=dao.boardCount(search, key);
	}else{
		totcount=dao.boardCount();
	}
	
	//페이지 처리
	int nowpage=1;
	int maxlist=10;
	int totpage=1;
	
	if(totcount%maxlist==0){
		totpage=totcount/maxlist;
	}else{
		totpage=totcount/maxlist+1;
	}
	if(totpage==0) totpage=1;
	
	if(request.getParameter("page")!=null){
		nowpage=Integer.parseInt(request.getParameter("page"));
	}
	
	int pagestart=(nowpage-1)*maxlist+1;
	int endpage=nowpage*maxlist;
	int listcount=totcount-((nowpage-1)*maxlist);
	
	//게시글목록 불러오기
	if(key.equals("")){
		list=dao.boardList(pagestart, endpage);
	}else{
		list=dao.boardList(search, key, pagestart, endpage);
	}
	
	//페이지 인덱싱 처리
	String pageSkip="";
	if(key.equals("")){
		pageSkip=PageIndex.pageList(nowpage, totpage, url, addtag);
	}else{
		pageSkip=PageIndex.pageListHan(nowpage, totpage, url, search, key);
	}
%>
<html>
<head><title>게시판 읽기</title>
<link rel="stylesheet" type="text/css" href="/stylesheet.css">
<style type="text/css">
  a.list {text-decoration:none;color:black;font-size:10pt;}
</style>
<script>
	function board_search(){
		if(board.key.value==""){
			alert("검색어를 입력하세요");
			board.key.focus();
			return;
		}
		board.submit();
	}
</script>
</head>
<body bgcolor="#FFFFFF" topmargin="0" leftmargin="0">
<table border="0" width="800">
  <tr>
    <td width="20%" height="500" valign="top" bgcolor="#ecf1ef">

	<!-- 다음에 추가할 부분 --></td>

	<td width="80%" valign="top">	
		<br>
    <table border="0" cellspacing="1" width="100%" align="center">
      <tr>
        <td colspan="7" align="center" valign="center" height="20">
        <font size="4" face="돋움" color="blue">
        <img src="./img/bullet-01.gif"> <b>자 유 게 시 판</b></font></td></tr>
      <tr>
        <td colspan="5" align="right" valign="middle" height="20">
		<font size="2" face="고딕">전체 : <b><%= totcount %></b>건 - <%= nowpage %>/ <%= totpage %> Pages</font></td></tr>
 	   <tr bgcolor="e3e9ff">
 	      <td width="10%" align="center" height="20"><font face="돋움" size="2">번 호</font></td>
 	      <td width="50%" align="center" height="20"><font face="돋움" size="2">제 목</font></td>
 	      <td width="15%" align="center" height="20"><font face="돋움" size="2">글쓴이</font></td>
 	      <td width="15%" align="center" height="20"><font face="돋움" size="2">작성일</font></td>
 	      <td width="10%" align="center" height="20"><font face="돋움" size="2">조회수</font></td>
 	   </tr>
<%
	for(BoardDTO dto: list){
%>
		<tr onMouseOver="style.backgroundColor='#D1EEEE'" onMouseOut="style.backgroundColor=''">
			<td align="center" height="25">
			<font face="돋움" size="2" color="#000000"><%= listcount-- %></font></td>
			<td align="left" height="20">&nbsp;
				<font face="돋움" size="2" color="#000000">
				<a class="list" href="board_view.jsp?idx=<%= dto.getIdx() %>&page=<%= nowpage %>"><%= dto.getSubject() %></a></td>
					<td align="center" height="20"><font face="돋움" size="2">
					<a class="list" href="mailto:<%= dto.getEmail() %>"><%= dto.getName() %></a></font></td>
				<td align="center" height="20"><font face="돋움" size="2"><%= dto.getRegdate().substring(0,10) %></font></td>
				<td align="center" height="20"><font face="돋움" size="2">
				<%= dto.getReadcnt() %></font></td>
		</tr>
<%
	}
%>		
	 <div align="center">
        <table width="700" border="0" cellspacing="0" cellpadding="5">
          <tr>&nbsp;</tr><tr>
             <td colspan="5">        
                <div align="center"><%= pageSkip %></div>
			  </td>
			 </tr>
		</table>

	<table width="600">
		<tr>
			<td width="25%"> &nbsp;</td>
			<td width="50%" align="center">
				<table>
					<form name="board" method="post" action="board_list.jsp">	
					<!-- 검색어를 이용하여 글제목, 작성자, 글내용 중에 하나를 입력 받아 처리하기 위한 부분 -->
						<tr>
							<td>
								<select name="search">
									<option value="subject" <% if(search.equals("subject")){ %> selected <% } %>>글제목</option>
									<option value="name" <% if(search.equals("name")){ %> selected <% } %>>작성자</option>
									<option value="contents" <% if(search.equals("contents")){ %> selected <% } %>>글내용</option>
								</select>
							</td>
							<td> <input type="text" size=20 name="key" value="<%= key %>"></td>
							<td> <a href="javascript:board_search()"><img src="./img/search2.gif" border="0"></a></td>
						</tr>
					</form>
				</table>
			</td>
			<td width="25%" align="right">
			<a href="board_write.jsp?page=<%= nowpage %>"><img src="./img/write.gif" border="0"></a>
			</td>
		</tr>
	</table>
</body>
</html>

