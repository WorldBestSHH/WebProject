<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.sql.*" %>
<%
	String myDriver="oracle.jdbc.driver.OracleDriver";
	String myURL="jdbc:oracle:thin:@localhost:1521:xe";
	String myID="track2_12";
	String myPass="1234";
	
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	try{
		Class.forName(myDriver);
		conn=DriverManager.getConnection(myURL,myID,myPass);
		pstmt=conn.prepareStatement("select * from tbl_member order by first_time");
		rs=pstmt.executeQuery();
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		
	}
%>
<html>
<head>
<title>회원목록 보여주기</title>

<STYLE TYPE="text/css">
<!--
body { font-family: 돋움, Verdana; font-size: 9pt}
td   { font-family: 돋움, Verdana; font-size: 9pt; text-decoration: none; color: #000000} 
a.list { text-decoration:none;color:black;font-size:10pt; }
--->
</STYLE>
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
	while(rs.next()){
		String tel=rs.getString("tel").substring(0,3)+"-"
				+rs.getString("tel").substring(3,7)+"-"+rs.getString("tel").substring(7);
%>   
   <tr>
      <td align=center><%= cnt %></td>
      <td align=center><a href="userinfo_modify.jsp?userid=<%= rs.getString("userid") %>"><%= rs.getString("userid") %></a></td>
      <td align=center><%= rs.getString("name") %></td>
      <td align=center><%= tel %></td>
      <td align=center><%= rs.getString("first_time").substring(0,10) %></td>
      <td align=center><%= rs.getString("last_time") %></td>
   </tr>
<%
		cnt++;
	}
%>
</table>
<table width=550>
  <tr>
    <td>
      <select name="search_gubun">
        <option >이름 </option>
        <option >주소 </option>
        
    </td>
    <td>  찾는이름:
          <input type="text" name="searc" size=10> &nbsp;
          [조회]
     </td>
   </tr>
  <tr>
    <td>
    </td>
    <td></td>
   </tr>
  <tr>
    <td><a class="list" href="userlogin_form.jsp">로그인 페이지 이동</a>
    </td>
    <td><a class="list" href="userinfo_insert.jsp">회원가입페이지 이동</a></td>
   </tr>
</table>    
</body>
</html>
