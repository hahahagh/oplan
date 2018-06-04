<%@page import="java.util.Date"%>
<%@page import="com.test.domain.Comment"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 리플달기 -->
	<div>
		<form action="addComment" name="fr" method="post">
			<input type="hidden" name="member_id" value="${id}"> 
			<input type="hidden" name="restaurant_num" value="${param.num }">
			<input type="hidden" name="pageNum" value="${param.pageNum }">	
			<textarea rows="4" cols="100" name="content"></textarea>
			<input type="submit" value="등록">
		</form>
	</div>

</body>
</html>