<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.sql.*" %>
<% 
	String myDriver="oracle.jdbc.driver.OracleDriver";
	String myURL="jdbc:oracle:thin:@localhost:1521:xe";
	String myID="track2_12";
	String myPass="1234";
%>
<%
	int idx=Integer.parseInt(request.getParameter("idx"));
	
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	try{
		Class.forName(myDriver);
		conn=DriverManager.getConnection(myURL,myID,myPass);
		pstmt=conn.prepareStatement("select * from tbl_guest where idx=?");
		pstmt.setInt(1, idx);
		rs=pstmt.executeQuery();
		rs.next();
	}catch(Exception e){
		e.printStackTrace();
	}
%>
 <html>
   <head><title>방명록 수정</title>
    <link rel="stylesheet" type="text/css" href="/stylesheet.css">
<script>
	function send(){
		if(guest.subject.value==""){
			alert("제목을 입력하세요");
			guset.subject.focus();
			return;
		}
		if(guest.contents.value==""){
			alert("내용을 입력하세요");
			guset.contents.focus();
			return;
		}
		guest.action="guest_modify_pro.jsp";
		guest.submit();
	}
</script>
</head>

 <body topmargin="0" leftmargin="0">
 <table border="0" width="800">
 <tr>
   <td width="20%" height="500" bgcolor="#ecf1ef" valign="top">

   <!-- 다음에 추가할 부분 "-->

   </td>
   <td width="80%" valign="top">&nbsp;<br>
     <img src="./img/bullet-01.gif"><font size="3" face="돋움" color="blue"> <b>방 명 록</b></font>
     <font size="2"> - 수정하기</font><p>
     <img src="./img/bullet-03.gif"><font size="2" face="돋움" color="orange"> 잠깐</font> &nbsp;
     <img src="./img/bullet-02.gif"><font size="2" face="돋움">는 필수 입력 사항입니다.</font><p>
     
	<form name="guest" method="post">
	<input type="hidden" name="idx" value="<%= rs.getInt("idx") %>">
      <table border="0">
       <tr>
         <td width="5%" align="right"><img src="./img/bullet-02.gif"></td>
         <td width="15%"><font size="2" face="돋움">글쓴이</font></td>
         <td width="80%">
         <input type="text" size="20" name="name" value="<%= rs.getString("name") %>" readonly></td>
       </tr>
       <tr>
         <td align="right"><img src="./img/bullet-02.gif"></td>
         <td><font size="2" face="돋움">제목</font></td>
         <td><input type="text" size="60" name="subject" value="<%= rs.getString("subject") %>"></td>
       </tr>
       <tr>
         <td align="right"><img src="./img/bullet-02.gif"></td>
         <td><font size="2" face="돋움">내용</font></td>
         <td><textarea wrap="physical" rows="10" name="contents" cols="60" ><%= rs.getString("contents") %></textarea></td>
       </tr>
        <tr>
          <td align="right">&nbsp;</td>
          <td><font size="2">&nbsp;</font></td>
          <td><input type="button" value="수정하기" onClick="send()">&nbsp;
          <input type="button" value="돌아가기" onClick="history.back()"></td></tr>
      </table>
      </form>
    </td></tr>
  </table>
  </body>
  </html>
   