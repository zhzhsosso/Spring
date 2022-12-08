<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>remove.jsp</h1>
	
	<fieldset>
		<legend>회원삭제</legend>
		<!-- action="/컨트롤러주소/이동할주소" -->
		<form action="" method="post">
			<input type="hidden" name="userid" value=${id }><br>
			비밀번호 : <input type="password" name="userpw"><br>
			<input type="submit" value="회원탈퇴">
		</form>
	</fieldset>
</body>
</html>