<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>login.jsp</h1>
	
	<fieldset>
		<legend>로그인</legend>
		<!-- action="/컨트롤러주소/이동할주소" -->
		<form action="/member/login" method="post">
			아이디 : <input type="text" name="userid"><br>
			비밀번호 : <input type="password" name="userpw"><br>
			<input type="submit" value="로그인">
		</form>
	</fieldset>
</body>
</html>