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
	int idx=Integer.parseInt(request.getParameter("idx"));
	String pass=request.getParameter("pass");
	Connection conn=null;
	PreparedStatement pstmt=null;
	int row=0;
	try{
		Class.forName(myDriver);
		conn=DriverManager.getConnection(myURL,myID,myPass);
		String sql="delete from tbl_board where idx=? and pass=?";
		pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, idx);
		pstmt.setString(2, pass);
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
		alert("삭제되었습니다.");
		window.opener.location.replace("board_list.jsp"); //부모
		self.close();
	</script>
<% 
	}else{
%>	
	<script>
		alert("비밀번호를 확인해주세요.");
		history.back();
	</script>
<%
	}
%>