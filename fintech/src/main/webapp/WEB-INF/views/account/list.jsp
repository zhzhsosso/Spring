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

		<h1>views/account/list.jsp</h1>
		
		<table border="1">
			<tr>
				<th>계좌번호</th>
				<th>은행명</th>
				<th>예금주</th>
			</tr>
			
			<c:forEach var="account" items="${accountList.res_list}">
			<tr>
				<td>${account.account_num_masked }</td>
				<td>${account.bank_name }</td>
				<td>${account.account_holder_name }</td>
			</tr>
			</c:forEach>
		</table>

</body>
</html>