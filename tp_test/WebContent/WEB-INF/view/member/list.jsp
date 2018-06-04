<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 목록</title>
</head>
<c:if test="${id eq null or id ne 'admin'}">
    <c:redirect url="main"/>
</c:if>
<body>
	<h1>회원목록</h1>
	<table border="1">
		<tr><th>아이디</th><th>패스워드</th><th>이름</th><th>성별</th><th>나이</th><th>이메일</th><th>가입일자</th></tr>
		<c:forEach var="member" items="${members}">
			<tr>
				<td>${member.id}</td>
				<td>${member.passwd}</td>
				<td>${member.name}</td>
				<td>${member.gender}</td>
				<td>${member.age}</td>
				<td>${member.email}</td>
				<td>${member.reg_date}</td>
			</tr>
		</c:forEach>
	</table>
	<h3>
		<a href="main">메인화면</a>
	</h3>
</body>
</html>