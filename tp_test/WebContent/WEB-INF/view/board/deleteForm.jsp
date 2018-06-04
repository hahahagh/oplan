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
<h1>게시판 글삭제</h1>
<form action="delete?pageNum=${param.pageNum}" method="post">
<input type="hidden" name="num" value="${param.num}">
<table border="1">
	<tr>
		<th>패스워드</th>
		<td><input type="password" name="passwd"></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="글삭제">
			<input type="reset" value="다시쓰기">
			<input type="button" value="글목록" onclick="location.href='list?pageNum=${param.pageNum}'">	
		</td>
	</tr>
</table>
</form>
</body>
</html>









