<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>멤버 메인화면</title>
</head>
<c:if test="${id eq null}">
    <c:redirect url="login"/>
</c:if>
<body>
	<h1>메인페이지</h1>
	${id}님 로그인 하셨습니다.
	<input type="button" value="로그아웃" onclick="location.href='logout'"><br>
	<a href="get">회원정보 조회</a><br>
	<a href="update">회원정보 수정</a><br>
	<a href="delete">회원정보 삭제</a><br>
<c:if test="${id ne null and id eq 'admin'}">
	<a href="list">회원목록</a><br>
</c:if>	
	<a href="../board/write">게시판 글쓰기</a><br>
	<a href="../board/list">게시판 글목록</a><br>
	<a href="../restaurant/add">레스토랑 등록</a>
	<a href="../restaurant/list">레스토랑 리스트</a>
</body>
</html>