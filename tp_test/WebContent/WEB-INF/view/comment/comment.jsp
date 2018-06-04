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
		<form action="addComment?num=${restaurant.num}&pageNum=${pageNum}"
			name="fr" method="post">
			<input type="hidden" name="member_id" value="${member.id}"> <input
				type="hidden" name="restaurant_num" value="${restaurant.num }">
			<textarea rows="4" cols="100" name="content"></textarea>
			<input type="submit" value="등록">
		</form>
	</div>
	<!-- 댓글 뿌려지는 곳 -->
	<div>
		<table align="center">
			<c:choose>
				<c:when test="${list.size !=0 }">
					<%
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
					%>
					<c:forEach var="comment" items="${comments }">
						<%
							Timestamp timestamp = ((Comment) pageContext.getAttribute("comment")).getReg_date();
						%>
						<%
							Date date = new Date(timestamp.getTime());
						%>
						<tr>
							<td>${comment.num}</td>
							<td><a href="detail?num=${comment.num}&pageNum=${pageNum}">${comment.content}</a>
							</td>
							<td>${comment.member_id}</td>
							<td><%=sdf.format(date)%></td>
							<td>${comment.readcount}</td>
							<td>${comment.ip}</td>
						</tr>
					</c:forEach>
				</c:when>

				<c:otherwise>
					<tr>
						<td colspan="6">게시판 글 없음</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>

	</div>
</body>
</html>