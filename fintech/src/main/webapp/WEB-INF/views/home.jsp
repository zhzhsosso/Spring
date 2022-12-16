<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<!-- 인증 요청 작업 수행 -->
<form action="https://testapi.openbanking.or.kr/oauth/2.0/authorize" method="get">
<!-- 고정값: code -->
<input type="hidden" name="response_type" value="code">
<!-- 오픈뱅킹에서 발급한 이용기관 앱의 Client ID -->
<input type="hidden" name="client_id" value="be6aa2c4-8b34-4401-9c54-49643df0e522">
<!-- 사용자인증이 성공하면 이용기관으로 연결되는 URL callback_uri -->
<input type="hidden" name="redirect_uri" value="http://localhost:8080/fintech/callback">
<!-- Access Token 권한 범위 -->
<input type="hidden" name="scope" value="login inquiry transfer">
<!-- CSRF 보안위협에 대응하기 위해 이용기관이 세팅하는 난수값 32자리 -->
<input type="hidden" name="state" value="12345678123456781234567812345678">
<!-- 사용자인증타입 구분주 2)(0:최초인증, 1:재인증, 2:인증생략) -->
<input type="hidden" name="auth_type" value="0">

<input type="submit" value="인증하고 토큰발급">
</form>

</body>
</html>
