<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원정보 수정</title>
</head>
<c:if test="${id eq null}">
    <c:redirect url="loginForm"/>
</c:if>
<body>
	<h1>회원 수정</h1>
	<form action="update" method="post">
		<label for="id">아이디</label> :
		<input type="text" name="id" id="id" value="${member.id}" readonly="readonly">
		<br>
		<label for="passwd">패스워드</label> :
		<input type="password" name="passwd" id="passwd">
		<br>
		<label for="name">이름</label> :
		<input type="text" name="name" id="name" value="${member.name}">
		<br>
		<label for="age">나이</label> :
		<input type="text" name="age" id="age" value="${member.age}">
		<br>
		성별 :
		<input type="radio" name="gender" id="male" value="남" <c:if test="${member.gender eq null or member.gender eq '남'}">checked="checked"</c:if>>
		<label for="male">남성</label>
		<input type="radio" name="gender" id="female" value="여" <c:if test="${member.gender ne null and member.gender eq '여'}">checked="checked"</c:if>>
		<label for="female">여성</label>
		<br>
		<label for="email">이메일</label> :
		<input type="email" name="email" id="email" value="${member.email}">
		<br>
		<input type="submit" value="회원수정">
	</form>
</body>
</html>