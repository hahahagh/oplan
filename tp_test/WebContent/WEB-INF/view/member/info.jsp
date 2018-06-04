<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원정보 조회</title>
</head>
<c:if test="${id eq null}">
    <c:redirect url="loginForm"/>
</c:if>
<body>
	<h1>회원정보 조회</h1>
	아이디 : ${member.id}<br>
	패스워드 : ${member.passwd}<br>
	이름 : ${member.name}<br>
	가입날짜 : ${member.reg_date}<br>
	나이 : ${member.age}<br>
	성별 : ${member.gender}<br>
	이메일 : ${member.email}<br>
	<a href="main">메인화면</a>
</body>
</html>