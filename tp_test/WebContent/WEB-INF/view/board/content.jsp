<%@page import="com.test.dao.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<c:if test="${id eq null}">
    <c:redirect url="/app/member/loginForm"/>
</c:if>
<body>
<h1>글내용 보기</h1>
<table border="1">
	<tr>
		<th>글번호</th><td>${board.num}</td>
		<th>조회수</th><td>${board.readcount}</td>
	</tr>
	<tr>
		<th>작성자</th><td>${board.name}</td>
		<th>작성일</th><td>${board.reg_date}</td>
	</tr>
	<tr>
		<th>글제목</th>
		<td colspan="3">${board.subject}</td>
	</tr>
	<tr>
		<th>파일</th>
		<td colspan="3">
			<a href="../../upload/${board.filename}">${board.filename}</a>
			<img src="../../upload/${board.filename}" width="50" height="50">
		</td>
	</tr>
	<tr>
		<th>글내용</th>
		<td colspan="3"><pre>${board.content}</pre></td>
	</tr>
	<tr>
		<td colspan="4">
			<input type="button" value="글수정" onclick="location.href='update?num=${board.num}&pageNum=${pageNum}'">
			<input type="button" value="글삭제" onclick="location.href='delete?num=${board.num}&pageNum=${pageNum}'">
			<input type="button" value="답글쓰기" onclick="location.href='rewrite?re_ref=${board.re_ref}&re_lev=${board.re_lev}&re_seq=${board.re_seq}&pageNum=${pageNum}'">
			<input type="button" value="글목록" onclick="location.href='list?pageNum=${pageNum}'">
		</td>
	</tr>
</table>
</body>
</html>












