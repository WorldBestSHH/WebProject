<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.sql.*"%>
<%
	String myDriver="oracle.jdbc.driver.OracleDriver";
	String myURL="jdbc:oracle:thin:@localhost:1521:xe";
	String myID="track2_12";
	String myPass="1234";
%>
<%
	String userid=request.getParameter("userid");
	
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	try{
		Class.forName(myDriver);
		conn=DriverManager.getConnection(myURL,myID,myPass);
		String sql="select * from tbl_member where userid=?";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, userid);
		rs=pstmt.executeQuery();
		rs.next();
	}catch(Exception e){
		e.printStackTrace();
	}
%>

<html>
<head>
<title>회원수정</title>
<STYLE TYPE="text/css">
<!--
body { font-family: 돋움, Verdana; font-size: 9pt}
td   { font-family: 돋움, Verdana; font-size: 9pt; text-decoration: none; color: #000000; BACKGROUND-POSITION: left top; BACKGROUND-REPEAT: no-repeat;}
-->
.formbox {
	BACKGROUND-COLOR: #F0F0F0; FONT-FAMILY: "Verdana", "Arial", "Helvetica", "돋움"; FONT-SIZE:9pt
} 
--->
</STYLE>
<script>
	function post_check(){
		window.open("post_check.jsp","post_check","x=150,y=200,width=350,height=230");
	}
	function send(){
		//비밀번호 passwd(password) 
		if(!userinfo.passwd.value){
			alert("비밀번호를 입력하세요");
			userinfo.passwd.focus();
			return;
		}
		//전화번호 tel(text)
		var pattern2=/^[0-9]{11}$/;
		if(!userinfo.tel.value){
			alert("전화번호를 입력하세요");
			userinfo.tel.focus();
			return;
		}
		else if(!pattern2.test(userinfo.tel.value)){
			alert("01011112222의 형태로 입력하세요");
			userinfo.tel.focus();
			return;
		}
		//E-mail email(text)
		if(!userinfo.email.value){
			alert("이메일을 입력하세요");
			userinfo.email.focus();
			return;
		}
		userinfo.submit();
	}
	function del(){
		userinfo.reset();
	}
</script>
</head>

<body bgcolor="#FFFFFF" LEFTMARGIN=0  TOPMARGIN=0 >
 
 <!-- 탑 메뉴 영역 삽입-->


<table border="0" width="800">
<tr>
  <td width="20%"  bgcolor="#ecf1ef" valign="top" style="padding-left:0;">
	
	<!--로그인 영역 삽입-->

  </td>
  <td width="80%" valign="top">&nbsp;<img src="./img/title1.gif" ><br>    
	<form name="userinfo" method="post" action="userinfo_modify_pro.jsp">
	<table border=0 cellpadding=0 cellspacing=0 border=0 width=730 valign=top>
		<tr><td align=center><br>                            
			<table cellpadding=0 cellspacing=0 border=0 width=650 align=center>       
				<tr>
					<td bgcolor="#7AAAD5">            
						<table cellpadding=0 cellspacing=0 border=0 width=100%>
							<tr bgcolor=#7AAAD5>
								<td align=left BORDER="0" HSPACE="0" VSPACE="0"><img src="./img/u_b02.gif"></td>
								<td align=center bgcolor="#7AAAD5"><FONT COLOR="#FFFFFF"><b>사용자정보수정&nbsp;</b><font color=black>(</font><font color=red>&nbsp;*&nbsp;</font><font color=black>표시항목은 반드시 입력하십시요.)</font></FONT></td>
								<td align=right BORDER="0" HSPACE="0" VSPACE="0"><img src="./img/u_b03.gif"></td>
							</tr>
						</table>
						<table cellpadding=3 cellspacing=1 border=0 width=100%>
							<tr>
								<td width=110 bgcolor=#EFF4F8>&nbsp;회원 성명<font color=red>&nbsp;*</font></td>
								<TD BGCOLOR=WHITE>
									<input type=text name=name size=16 maxlength=20 value="<%= rs.getString("name") %>" readonly> 변경할 수 없습니다.
								</td>
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;회원 ID<font color=red>&nbsp;*</font></td>
								<TD BGCOLOR=WHITE>
									<table cellspacing=0 cellpadding=0>
										<tr>
											<td align=absmiddle>
												<input type=text name=userid size=12 maxlength=16 style="width:120" value="<%= rs.getString("userid") %>" readonly> 변경할 수 없습니다.
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;비밀번호<font color=red>&nbsp;*</font></td>
								<TD BGCOLOR=WHITE>
									<input type=password name=passwd size=8 maxlength=12 style="width:80">
											비밀번호를 입력하세요.
								</td>
							</tr>
							
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;주소구분<font color=red>&nbsp;</font></td>
								<TD BGCOLOR=WHITE>
									<input type=radio name=gubun value="직장" <% if(rs.getString("gubun")!=null&&rs.getString("gubun").equals("직장")){ %> checked <% } %>>직장&nbsp;&nbsp;
									<input type=radio name=gubun value="자택" <% if(rs.getString("gubun")!=null&&rs.getString("gubun").equals("자택")){ %> checked <% } %>>자택 
								</td>
							</tr>
							
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;우편번호<font color=red>&nbsp;</font></td>
								<TD BGCOLOR=WHITE>
									<table cellspacing=0 cellpadding=0>
										<tr>
											<td><input type=text name=zip size=6 maxlength=6 ></td>
                  							<td><a href="javascript:post_check()"><img src="./img/u_bt07.gif" hspace=2 border=0 name=img2 align=absmiddle></a></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;주소<font color=red>&nbsp;</font></td>
								<TD BGCOLOR=WHITE>
									<input type=text name=addr1 size=50 maxlength=100>
								</td>
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;나머지 주소<font color=red>&nbsp;</font></td>
								<TD BGCOLOR=WHITE>
									<input type=text name=addr2 size=50 maxlength=100>
								</td>
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;전화번호<font color=red>&nbsp;*</font></td>
								<TD BGCOLOR=WHITE>
									<input type=text name=tel size=13 maxlength=13  value="<%= rs.getString("tel") %>" >
								</td>
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;E-mail<font color=red>&nbsp;*</font>
								</td>
								<td bgcolor=WHITE valign=middle>
									<input type=text name=email size=30 maxlength=30  value="<%= rs.getString("email") %>" >
								</td>
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;관심분야
                					<font color=red>&nbsp;</font>
								</td>
								<td bgcolor=WHITE valign=middle> 
									<input type="checkbox" name="fa" value="건강" <% if(rs.getString("favorite")!=null&&rs.getString("favorite").contains("건강")) {%> checked <% } %>>건강 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="checkbox" name="fa" value="문화예술" <% if(rs.getString("favorite")!=null&&rs.getString("favorite").contains("문화예술")) {%> checked <% } %>>문화예술 &nbsp;&nbsp;
									<input type="checkbox" name="fa" value="경제" <% if(rs.getString("favorite")!=null&&rs.getString("favorite").contains("경제")) {%> checked <% } %>>경제 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="checkbox" name="fa" value="연예오락" <% if(rs.getString("favorite")!=null&&rs.getString("favorite").contains("연예오락")) {%> checked <% } %>>연예오락 &nbsp;
									<input type="checkbox" name="fa" value="뉴스" <% if(rs.getString("favorite")!=null&&rs.getString("favorite").contains("뉴스")) {%> checked <% } %>>뉴스
									<br><input type="checkbox" name="fa" value="여행레져" <% if(rs.getString("favorite")!=null&&rs.getString("favorite").contains("여행레져")) {%> checked <% } %>>여행레져 &nbsp;&nbsp;
									<input type="checkbox" name="fa" value="생활" <% if(rs.getString("favorite")!=null&&rs.getString("favorite").contains("생활")) {%> checked <% } %>>생활 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="checkbox" name="fa" value="스포츠" <% if(rs.getString("favorite")!=null&&rs.getString("favorite").contains("스포츠")) {%> checked <% } %>>스포츠 &nbsp;&nbsp;
									<input type="checkbox" name="fa" value="교육" <% if(rs.getString("favorite")!=null&&rs.getString("favorite").contains("교육")) {%> checked <% } %>>교육 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="checkbox" name="fa" value="컴퓨터" <% if(rs.getString("favorite")!=null&&rs.getString("favorite").contains("컴퓨터")) {%> checked <% } %>>컴퓨터 &nbsp;&nbsp;
									<input type="checkbox" name="fa" value="학문" <% if(rs.getString("favorite")!=null&&rs.getString("favorite").contains("학문")) {%> checked <% } %>>학문 &nbsp;&nbsp;
								</td>
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;직업<font color=red>&nbsp;</font></td>
								<TD BGCOLOR=WHITE>
									<select name=job class="formbox">
 										<option value="0" <% if(rs.getString("job")==null) {%> selected <% } %>>선택하세요 ---
 										<option value="회사원" <% if(rs.getString("job")!=null&&rs.getString("job").contains("회사원")) {%> selected <% } %>>회사원
 										<option value="연구전문직" <% if(rs.getString("job")!=null&&rs.getString("job").contains("연구전문직")) {%> selected <% } %>>연구전문직
 										<option value="교수학생" <% if(rs.getString("job")!=null&&rs.getString("job").contains("교수학생")) {%> selected <% } %>>교수학생
 										<option value="일반자영업" <% if(rs.getString("job")!=null&&rs.getString("job").contains("일반자영업")) {%> selected <% } %>>일반자영업
 										<option value="공무원" <% if(rs.getString("job")!=null&&rs.getString("job").contains("공무원")) {%> selected <% } %>>공무원
 										<option value="의료인" <% if(rs.getString("job")!=null&&rs.getString("job").contains("의료인")) {%> selected <% } %>>의료인
 										<option value="법조인" <% if(rs.getString("job")!=null&&rs.getString("job").contains("법조인")) {%> selected <% } %>>법조인
 										<option value="종교,언론,에술인" <% if(rs.getString("job")!=null&&rs.getString("job").contains("종교,언론,에술인")) {%> selected <% } %>>종교.언론/예술인
 										<option value="농,축,수산,광업인" <% if(rs.getString("job")!=null&&rs.getString("job").contains("농,축,수산,광업인")) {%> selected <% } %>>농/축/수산/광업인
 										<option value="주부" <% if(rs.getString("job")!=null&&rs.getString("job").contains("주부")) {%> selected <% } %>>주부
 										<option value="무직" <% if(rs.getString("job")!=null&&rs.getString("job").contains("무직")) {%> selected <% } %>>무직
 										<option value="기타" <% if(rs.getString("job")!=null&&rs.getString("job").contains("기타")) {%> selected <% } %>>기타
									</select>
								</td>                
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;자기소개<font color=red>&nbsp;</font></td>
								<TD BGCOLOR=WHITE>
									<textarea cols=65 rows=5 name="intro"><%if(rs.getString("intro")!=null){%><%= rs.getString("intro") %><% } %></textarea>
								</td>
							</tr>
						</table>
						<table cellpadding=0 cellspacing=0 border=0 width=100%>
							<tr bgcolor=#7AAAD5>
								<td valign=bottom>
									<img src="./img/u_b04.gif" align=left hspace=0 vspace=0 border=0>
								</td>
								<td align=center></td>
								<td valign=bottom>
									<img src="./img/u_b05.gif" align=right hspace=0 vspace=0 border=0>
								</td>
							</tr>
							<tr bgcolor=#ffffff>
								<td colspan=3 align=center>
									<a href="#" onClick="send()"><img src="./img/u_bt06.gif" vspace=3 border=0 name=img3></a>
									<a href="#" onClick="del()"><img src="./img/u_bt05.gif" border=0 hspace=10 vspace=3 name=img4></a>
								</td>
							 </tr>
						</table> 
					</td>
				</tr>
			</td>
		</tr>
	</table>
	</form>
	</td>
</tr>
</table>

 <!-- copyright 영역 삽입-->
  

</body>
</html>
