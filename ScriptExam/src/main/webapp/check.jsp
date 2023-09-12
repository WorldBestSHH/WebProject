<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function send(){
		//성별체크(radio)
		<%-- var gender=document.getElementsByName("gender"); form name이 없을 때 꺼내오는 방법
			 alert(gender.length); --%>
		if(!check.gender[0].checked && !check.gender[1].checked){
			alert("성별을 선택하세요")
			return
		}
		
		//스포츠 체크 확인(checkbox)
		for(i=0, flag=0; i<check.sport.length; i++){
			if(check.sport[i].checked){
				flag=1;
				break;
			}
		}
		if(!flag){
			alert("스포츠가 선택되지 않았습니다.");
			return;
		}
		
		//직업(select)
		if(check.job.selectedIndex==0){
			alert("직업을 선택하세요")
			check.job.focus;
			return;
		}
		
		check.action="check_pro.jsp"; //form의 action을 이렇게 주는 경우도 있음
		check.submit(); //submit이 없으면 전송 안됨
	}
</script>
</head>
<body>
	<form name="check" method="post">
		<table width=300 border=1>
			<tr>
				<td>성별</td>
				<td>
					남자<input type=radio name=gender value=M>
					여자<input type=radio name=gender value=F>
				</td>
			</tr><tr>
				<td>스포츠</td>
				<td>
					축구<input type=checkbox name=sport value=축구>
					야구<input type=checkbox name=sport value=야구>
					농구<input type=checkbox name=sport value=농구>
					배구<input type=checkbox name=sport value=배구>
				</td>
			</tr>
			<tr>
				<td>직업</td>
				<td><select name=job>
						<option>직업을 선택하세요</option>
						<option value=공무원>공무원</option>
						<option value=경찰>경찰</option>
						<option value=교수>교수</option>
						<option value=학생>학생</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan=2 align=center><input type=button value=전송 onClick="send()"></td>
			</tr>
		</table>
	</form>
</body>
</html>