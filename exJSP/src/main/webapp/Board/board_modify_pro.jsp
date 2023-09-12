<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%
	String myDriver="oracle.jdbc.driver.OracleDriver";
	String myURL="jdbc:oracle:thin:@localhost:1521:xe";
	String myID="track2_12";
	String myPass="1234";
%>    
<%
	request.setCharacterEncoding("utf-8");
	int idx=Integer.parseInt(request.getParameter("idx"));
	String email=request.getParameter("email");
	String subject=request.getParameter("subject");
	String contents=request.getParameter("contents");
	
	//db연결
	Connection conn=null;
	PreparedStatement pstmt=null;
	int row=0;
	try{
		Class.forName(myDriver);
		conn=DriverManager.getConnection(myURL,myID,myPass);
		String sql="update tbl_board set email=?, subject=?, contents=? where idx=?";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, email);
		pstmt.setString(2, subject);
		pstmt.setString(3, contents);
		pstmt.setInt(4, idx);
		row=pstmt.executeUpdate();
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		try{
			pstmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	if(row==1){
%>
	<script>
		alert("수정되었습니다.");
		location.replace("board_list.jsp");
	</script>
<% 
	}else{
%>	
	<script>
		alert("잠시후 다시 수정하세요.");
		history.back();
	</script>
<%
	}
%>