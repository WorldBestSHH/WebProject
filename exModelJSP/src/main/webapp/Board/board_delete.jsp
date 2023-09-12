<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="model.board.*" %>
<%
	int nowpage=Integer.parseInt(request.getParameter("page"));
	int idx=Integer.parseInt(request.getParameter("idx"));
	BoardDAO dao=BoardDAO.getInstance();
	BoardDTO dto=dao.boardView(idx);
%>
<html>
<head><title>방명록 삭제</title>
 <link rel="stylesheet" type="text/css" href="/stylesheet.css">
 </head>
 <script>
 	function send(){
 		if(board.pass.value!=<%= dto.getPass() %>){
 			alert("비밀번호를 다시 입력하세요");
 			board.pass.focus();
 			retrun;
 		}
 		board.action="board_delete_pro.jsp";
 		board.submit();
 	}
 </script>
 <body>
 <form method="post" name="board">
 <input type="hidden" name="idx" value="<%= dto.getIdx() %>">
 <input type="hidden" name="page" value="<%= nowpage %>">
   <table border="0" cellpadding="0" cellspacing="0" width="300" align="center">
     <tr>
       <td height="50">
       <img src="./img/bullet-05.gif"><b><font size="3" color="red">잠깐 !!</font></b></td></tr>
     <tr>
       <td valign="middle" height="30">
       <font size="2" face="돋움">게시물은 작성하신 분만 삭제할 수 있습니다.<br>
       글작성시 입력한 비밀번호를 입력해 주세요...</font></td></tr>
     <tr>
       <td valign="middle" height="40">
       <font size="2" face="돋움">
       비밀번호 <input type="password" name="pass" size="8"></font>
       <input type="button" value="삭제" onClick="send()">
       <input type="button" value="닫기" onClick="self.close()"></td></tr>
   </table>
   </form>
 </body>
 </html>
