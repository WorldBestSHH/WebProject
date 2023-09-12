<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- <link rel="stylesheet" href="join.css"> css 적용방법 -->
<meta charset="UTF-8">
<title>회원가입</title>
<script>
	function send(){
		//이름(text)
		if(!join.name.value){
			alert("이름를 입력하세요");
			join.name.focus();
			return;
		}
		//아이디(text)
		if(!join.userid.value){
			alert("아이디를 입력하세요");
			join.userid.focus();
			return;
		}
		//비밀번호(password)
		if(!join.userpw.value){
			alert("비밀번호를 입력하세요");
			join.userpw.focus();
			return;
		}
		//비밀번호확인
		if(join.userpw.value!=join.userpwch.value){
			alert("비밀번호를 확인하세요");
			join.userpwch.focus();
			return;
		}
		//email(text)
		if(!join.email1.value || !join.email2.value){
			alert("이메일을 입력하세요");
			if(!join.email1.value)
				join.email1.focus();
			else if(!join.email2.value)
				join.email2.focus();
			return;
		}
		//전화번호(text)
		if(!join.tel2.value || !join.tel3.value){
			alert("전화번호를 입력하세요");
			if(!join.tel2.value)
				join.tel2.focus();
			else if(!join.tel3.value)
				join.tel3.focus();
			return;
		}
		//성별(radio)
		if(!join.gender[0].checked && !join.gender[1].checked){
			alert("성별을 선택하세요");
			return
		}
		//취미(checkbox)
		for(i=0,flag=0;i<join.hobby.length;i++){
			if(!join.hobby[i].checked){
				flag=1;
				break;
			}
		}
		if(!flag){
			alert("취미를 선택하세요");
			return
		}
		//직업(select)
		if(join.job.selectedIndex==0){
			alert("직업을 선택하세요");
			join.job.focus;
			return;
		}
		
		join.submit();
	}
</script>
</head>
<body>
	<form name="join" method="post" action="join_pro.jsp">
		<table width="380"  height=500 style ="border-collapse:collapse">
			<tr>
				<td colspan="3" align="center"><h2>회원가입</h2></td>
			</tr>
			<tr>
				<td width=20></td>
				<td width=100>이름</td>
				<td><input type="text" name="name" style="width:150px;"></td>
			</tr>
			<tr>
				<td width=20></td>
				<td width=100>아이디</td>
				<td><input type="text" name="userid" style="width:150px;"></td>
			</tr>
			<tr>
				<td width=20></td>
				<td width=100>비밀번호</td>
				<td><input type="password" name="userpw" style="width:150px;"></td>
			</tr>
			<tr>
				<td width=20></td>
				<td width=100>비번확인</td>
				<td><input type="password" name="userpwch" style="width:150px;"></td>
			</tr>
			<tr>
				<td width=20></td>
				<td width=100>이메일</td>
				<td><input type="text" name="email1" style="width:100px;"> @
					<input type="text" name="email2" style="width:100px;"></td>
			</tr>
			<tr>
				<td width=20></td>
				<td width=100>전화</td>
				<td><select name="tel1">
						<option value="010">010</option>
						<option value="011">011</option>				
						<option value="019">019</option>
					</select>				
					- <input type="text" name="tel2" style="width:50px;">
					- <input type="text" name="tel3" style="width:50px;">
				</td>
			</tr>
			<tr>
				<td width=20></td>
				<td width=100>성별</td>
				<td>
					<input type="radio" name="gender" value="M">남
					<input type="radio" name="gender" value="F">여
				</td>
			</tr>
			<tr>
				<td width=20></td>
				<td width=100>취미</td>
				<td><input type="checkbox" name="hobby" value="운동">운동
					<input type="checkbox" name="hobby" value="영화">영화
					<input type="checkbox" name="hobby" value="여행">여행
					<input type="checkbox" name="hobby" value="독서">독서
				</td>
			</tr>
			<tr>
				<td width=20></td>
				<td width=100>직업</td>
				<td><select name="job">
						<option>직업을 선택하세요</option>
						<option value="공무원">공무원</option>
						<option value="교사">교사</option>
						<option value="군인">군인</option>
						<option value="학생">학생</option>
					</select>
				</td>
			</tr>
			<tr>
				<td width=20></td>
				<td colspan="3" align="center">
					<input type="button" value="등록" onClick="send()">&nbsp;&nbsp;&nbsp;
					<input type="reset" value="취소">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>