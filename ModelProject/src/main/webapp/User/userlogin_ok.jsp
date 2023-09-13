<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${row==0 }">
	<script>
		alert("비밀번호 오류입니다");
		history.back();
	</script>
</c:if>
<c:if test="${row=-1 }">
	<script>
		alert("아이디가 존재하지 않습니다");
		history.back();
	</script>
</c:if>
<c:if test="${row==0 }">
	<script>
		
	</script>
</c:if>