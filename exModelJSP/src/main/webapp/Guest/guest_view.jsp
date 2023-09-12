<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="model.guest.*" %>
<%
	int idx=Integer.parseInt(request.getParameter("idx"));
	int nowpage=Integer.parseInt(request.getParameter("page"));
	GuestDAO dao=new GuestDAO();
	//조회수 증가 메소드 호출
	dao.guestHits(idx);
	//특정게시글 검색
	GuestDTO dto=dao.guestSelect(idx);
%>
 <html>
 <head><meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
   <title>방명록 내용 보기</title>
   <link rel="stylesheet" type="text/css" href="/stylesheet.css">
   <style type="text/css">
     td.title { padding:4px; background-color:#e3e9ff }
     td.content { padding:10px; line-height:1.6em; text-align:justify; }
     a.list { text-decoration:none;color:black;font-size:10pt; }
   </style>
 <script>
 	function guest_del(idx, nowpage){
 		bool=confirm("글을 삭제합니다");
 		if(bool){
 			location.href="guest_del_pro.jsp?idx="+idx+"&page="+nowpage;
 		}
 	}
 </script>
 </head>
 <body topmargin="0" leftmargin="0">
   <table border="0" width="800">
     <tr>
       <td width="20%"  height="500" bgcolor="#ecf1ef" valign="top">

		 <!--  다음에 추가할 부분 -->

	   </td>
       <td width="80%" valign="top">&nbsp;<br>
         <table border="0" width="90%" align="center">
           <tr>
             <td colspan="2"><img src="./img/bullet-01.gif"> 
              <font color="blue" size="3">방 명 록</font><font size="2"> - 글읽기</font></td>
           </tr>
         </table>
       <p>
       <table border="0" width="90%" align="center" cellspacing="0"  style="border-width:1px;border-color:#0066cc;border-style:outset;">
         <tr bgcolor="e3e9ff">
           <td class="title">
             <img src="./img/bullet-04.gif"> <font size="2" face="돋움">
                  <%= dto.getSubject() %></font>
           </td>
         </tr>
         <tr>
           <td class="content">
             <p align="right"><font size="2" face="돋움">
              <%= dto.getName() %>  / <font size="2" face="돋움"><%= dto.getRegdate() %> / <%= dto.getReadcnt() %>번 읽음</font>
             <p>
             <%= dto.getContents().replace("\n", "<br>") %><p>
           </td>
         </tr>
       </table>

      <!--**** 여기서부터 게시물 내용 아래쪽의 버튼들이 나옵니다. 답변, 수정, 삭제, 목록보기 ****-->
      <p align="center">
      <font size="2">
       <!-- 목록보기 -->
       <a class="list" href="guest_list.jsp?page=<%= nowpage %>">[글목록]</a>&nbsp;&nbsp;&nbsp;&nbsp;
        <!-- 수정하기 -->
       <a class="list" href="guest_modify.jsp?idx=<%= dto.getIdx() %>&page=<%= nowpage %>">[글수정]</a>&nbsp;&nbsp;&nbsp;&nbsp;
        <!-- 삭제하기 -->
       <a class="list" href="javascript:guest_del(<%= dto.getIdx() %>, <%= nowpage %>)">[글삭제]</a>&nbsp;&nbsp;&nbsp;&nbsp;
        <!-- 글쓰기 -->
       <a class="list" href="guest_write.jsp?page=<%= nowpage %>">[글작성]</a>&nbsp;&nbsp;&nbsp;&nbsp;
      </font>
    </td>
  </tr>
  </table>
  </body>
  </html>
