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
<%-- request 속성값 가져오기 --%>

<c:set var="pageNum" value="${requestScope.pageNum}" />
<c:set var="restaurants" value="${requestScope.restaurants}" />

<body>
<h1>레스토랑 상세페이지</h1>
<table border="1">
	<tr>
		<th>등록번호</th><td>${restaurant.num}</td>
	</tr>
	<tr>
		<th>레스토랑이름</th><td>${restaurant.name}</td>	
	</tr>
	<tr>
		<th>등록일</th><td>${restaurant.reg_date}</td>
	</tr>
	<tr>
		<th>주소</th><td>${restaurant.address}</td>
	</tr>
	<tr>
		<th>매장전화번호</th><td>${restaurant.tel}</td>
	</tr>
	<tr>
		<th>파일</th>
		<td colspan="3">
			<a href="../../upload/${restaurant.filename}">${restaurant.filename}</a>
			<img src="../../upload/${restaurant.filename}" width="50" height="50">
		</td>
	</tr>
	<tr>
		<th>관심매장등록수</th><td>${restaurant.likecount}</td>
	</tr>
	<tr>
		<th>평점</th><td>${restaurant.score}</td>
	</tr>
	
	<tr>
		<td colspan="4">
			<input type="button" value="댓글달기" onclick="location.href='../comment/addComment?num=${restaurant.num}&pageNum=${pageNum}&id=${id }'">
			<input type="button" value="매장수정" onclick="location.href='update?num=${restaurant.num}&pageNum=${pageNum}'">
			<input type="button" value="매장삭제" onclick="location.href='delete?num=${restaurant.num}&pageNum=${pageNum}'">
			<input type="button" value="매장목록" onclick="location.href='list?pageNum=${pageNum}'">
		</td>
	</tr>
</table>

<table>
	<tr>
		<td>작성자:</td>
		<td>IP</td>
	</tr>
	<tr>
		<td>${comment.content }</td>
	</tr>
</table>
</body>
</html>