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
			<input type="submit" value="로그인" src="https://previews.123rf.com/images/sarahdesign/sarahdesign1403/sarahdesign140300871/26699464-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EC%95%84%EC%9D%B4%EC%BD%98-%EB%98%90%EB%8A%94-%EB%B2%84%ED%8A%BC-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EB%B2%84%ED%8A%BC-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EC%95%84%EC%9D%B4%EC%BD%98-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EC%95%84%EC%9D%B4%EC%BD%98-%EB%B2%84%ED%8A%BC-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EB%A1%9C%EA%B7%B8%EC%9D%B8.jpg"
				width="50"
			>
		</form>
			<input type="button" value="회원가입" onclick="location.href='/member/insert';">
	</fieldset>
</body>
</html>