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
	Connection conn = null;
	PreparedStatement pstmt = null;
	int row=0;
	try{
		Class.forName(myDriver);
		conn = DriverManager.getConnection(myURL, myID, myPass);
		String query="delete from tbl_guest where idx=?";
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, idx);
		row = pstmt.executeUpdate();
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
		alert("삭제되었습니다");
		location.replace("guest_list.jsp");
		//location.href="guest_list.jsp";
	</script>
<%
	}else{
%>	
	<script>
		alert("잠시후 다시 삭제하세요");
		history.back();
	</script>
<%
	}
%>	