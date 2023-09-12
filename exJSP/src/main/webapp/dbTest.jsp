<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>

<%
	try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		out.print("드라이버 로딩 성공");
	}catch(Exception e){
		e.printStackTrace();
	}

	Connection conn=null;
	PreparedStatement pstmt=null;
	
	try{
		conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe","track2_12","1234");
		out.print("conn : "+conn);
		pstmt=conn.prepareStatement("");
	}catch(Exception e){
		e.printStackTrace();
	}
%>