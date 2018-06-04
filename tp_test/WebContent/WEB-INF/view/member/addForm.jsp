<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
</head>
<body>
	<h1>회원가입</h1>
	<form action="add" method="post">
		<label for="id">아이디</label> :
		<input type="text" name="id" id="id">
		<br>
		<label for="passwd">패스워드</label> :
		<input type="password" name="passwd" id="passwd">
		<br>
		<label for="name">이름</label> :
		<input type="text" name="name" id="name">
		<br>
		<label for="age">나이</label> :
		<input type="text" name="age" id="age">
		<br>
		성별 :
		<input type="radio" name="gender" id="male" value="남"><label for="male">남성</label>
		<input type="radio" name="gender" id="female" value="여"><label for="female">여성</label>
		<br>
		<label for="email">이메일</label> :
		<input type="email" name="email" id="email">
		<br>
		<input type="submit" value="회원가입">
	</form>
</body>
</html>