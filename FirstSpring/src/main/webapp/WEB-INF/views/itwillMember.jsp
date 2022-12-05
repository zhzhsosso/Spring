<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>itwillMember.jsp</h1>
	
	${vo }<hr>
	아이디 : ${vo.userid }<br>
	비밀번호 : ${vo.userpw }<br>
	<hr><hr>
	${MemberVO }(대문자x)<hr>
	${memberVO }(소문자x)<hr>
</body>
</html>