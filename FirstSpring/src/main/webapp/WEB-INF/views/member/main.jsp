<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1> main.jsp</h1>
	${sessionScope.id }님 환영합니다
	${id }님 환영합니다<br>
	
	<input type="button" value="로그아웃" onclick="location.href='/member/'">
	
	<h3><a href="/member/info">회원정보 조회(select)</a></h3>
	
	<h3><a href="/member/modify">회원정보 수정(update)</a></h3>
	
	<h3><a href="/member/remove">회원정보 삭제(delete)</a></h3>
	
	<!-- <c:if test="${id == 'admin'}"></c:if> -->
	<c:if test="${id.equals('admin') }">
		<h3><a href="/member/list">회원 목록 보기(list)</a></h3>
	</c:if>
</body>
</html>