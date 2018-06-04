<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
</head>
<c:if test="${memberAddOkMessage ne null}">
    <h3>${memberAddOkMessage}</h3>
</c:if>
<c:if test="${loginFailMessage ne null}">
    <h3>${loginFailMessage}</h3>
</c:if>
<c:if test="${logoutMessage ne null}">
    <h3>${logoutMessage}</h3>
</c:if>
<body>
	<h1>로그인</h1>
	<form action="login" method="post">
		<label for="id">아이디</label> :
		<input type="text" name="id" id="id">
		<br>
		<label for="passwd">패스워드</label> :
		<input type="password" name="passwd" id="passwd">
		<br>
		<input type="submit" value="로그인">
	</form>
	<input type="button" value="회원가입" onclick="location.href='add'">
</body>
</html>