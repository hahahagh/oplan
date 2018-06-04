<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원정보 삭제</title>
</head>
<c:if test="${id eq null}">
    <c:redirect url="loginForm"/>
</c:if>
<body>
	<h1>회원 삭제</h1>
	<form action="delete" method="post">
		<label for="id">아이디</label> :
		<input type="text" name="id" id="id" value="${id}" readonly="readonly">
		<br>
		<label for="passwd">패스워드</label> :
		<input type="password" name="passwd" id="passwd">
		<br>
		<input type="submit" value="회원삭제">
	</form>
</body>
</html>