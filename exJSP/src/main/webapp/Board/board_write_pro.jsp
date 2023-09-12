<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String myDriver="oracle.jdbc.driver.OracleDriver";
	String myURL="jdbc:oracle:thin:@localhost:1521:xe";
	String myID="track2_12";
	String myPass="1234";
%>    
<%
	request.setCharacterEncoding("utf-8");
	String name=request.getParameter("name");
	String email=request.getParameter("email");
	String subject=request.getParameter("subject");
	String contents=request.getParameter("contents");
	String pass=request.getParameter("pass");
	
	//db연결
	Connection conn=null;
	PreparedStatement pstmt=null;
	int row=0;
	try{
		Class.forName(myDriver);
		conn=DriverManager.getConnection(myURL,myID,myPass);
		String sql="insert into tbl_board(idx,name,email,subject,contents,"
				+"pass) values(tbl_board_seq_idx.nextval,?,?,?,?,?)";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, name);
		pstmt.setString(2, email);
		pstmt.setString(3, subject);
		pstmt.setString(4, contents);
		pstmt.setString(5, pass);
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
		alert("등록되었습니다.");
		location.replace("board_list.jsp");
	</script>
<% 
	}else{
%>	
	<script>
		alert("잠시후 다시 등록하세요.");
		history.back();
	</script>
<%
	}
%>