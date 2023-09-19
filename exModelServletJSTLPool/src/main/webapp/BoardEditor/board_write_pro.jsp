<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${row==1}">
	<script>
		alert("등록성공");
		location.href="/BoardEditor/board_list.do?page=${page}";
	</script>
</c:if>
<c:if test="${row==0}">
	<script>
		alert("등록실패");
		history.back();
	</script>
</c:if>	
	