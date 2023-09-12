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
	String name = request.getParameter("name");
	String subject = request.getParameter("subject");
	String contents = request.getParameter("contents");

/*
	out.print("이름 : " + name + "<br>");
	out.print("제목 : " + subject + "<br>");
	out.print("내용 : " + contents + "<br>");
*/	
	//db 연결
	Connection conn = null;
	PreparedStatement pstmt = null;
	int row=0;
	try{
		Class.forName(myDriver);
		conn = DriverManager.getConnection(myURL, myID, myPass);
		String query="insert into tbl_guest(idx, name,subject, contents)" + 
				" values(tbl_guest_seq_idx.nextval, ?,?,?)";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, name);
		pstmt.setString(2, subject);
		pstmt.setString(3, contents);
		
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
		alert("등록되었습니다");
		location.replace("guest_list.jsp");
		//location.href="guest_list.jsp";
	</script>
<%
	}else{
%>	
	<script>
		alert("잠시후 다시 등록하세요");
		history.back();
	</script>
<%
	}
%>	