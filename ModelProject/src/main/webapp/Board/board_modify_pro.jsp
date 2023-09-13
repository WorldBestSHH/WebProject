<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:choose>
	<c:when test="${row==1}">
		<script>
			alert("글이 수정되었습니다");
			location.href="/Board/board_list.do?page=${page}";
		</script>
	</c:when>
	<c:otherwise>
		<script>
			alert("수정을 실패하였습니다");
			history.back();
		</script>
	</c:otherwise>
</c:choose>	
	