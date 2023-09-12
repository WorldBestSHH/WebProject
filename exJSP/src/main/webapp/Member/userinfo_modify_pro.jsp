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
	String name=request.getParameter("name");
	String userid=request.getParameter("userid");
	String passwd=request.getParameter("passwd");
	String gubun=request.getParameter("gubun");
	String zip=request.getParameter("zip");
	String addr1=request.getParameter("addr1");
	String addr2=request.getParameter("addr2");
	String tel=request.getParameter("tel");
	String email=request.getParameter("email");
	String fa[]=request.getParameterValues("fa");
	String job=request.getParameter("job");
	String intro=request.getParameter("intro"); 
	
	String favorite="";
	if(fa!=null){
		favorite=fa[0];
		for(int i=1;i<fa.length;i++){
			favorite=favorite+","+fa[i];
		}
	}
	
	if(job.equals("0")){
		job="";
	}
	
	Connection conn=null;
	PreparedStatement pstmt=null;
	int row=0;
	try{
		Class.forName(myDriver);
		conn=DriverManager.getConnection(myURL,myID,myPass);
		String sql="update tbl_member set passwd=?, gubun=?, zip=?, addr1=?, addr2=?,"
				+"tel=?, email=?, favorite=?, job=?, intro=? where userid=?";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, passwd);
		pstmt.setString(2, gubun);
		pstmt.setString(3, zip);
		pstmt.setString(4, addr1);
		pstmt.setString(5, addr2);
		pstmt.setString(6, tel);
		pstmt.setString(7, email);
		pstmt.setString(8, favorite);
		pstmt.setString(9, job);
		pstmt.setString(10, intro);
		pstmt.setString(11, userid);
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
		location.replace("userinfo_list.jsp");
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