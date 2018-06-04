<%@page import="com.test.domain.Restaurant"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
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
    <c:redirect url="/app/member/login"/>
</c:if>
<%-- request 속성값 가져오기 --%>
<c:set var="totalRowCount" value="${requestScope.totalRowCount}" />
<c:set var="pageNum" value="${requestScope.pageNum}" />
<c:set var="restaurants" value="${requestScope.restaurants}" />
<c:set var="pageCount" value="${requestScope.pageCount}" />
<c:set var="pageBlock" value="${requestScope.pageBlock}" />
<c:set var="startPage" value="${requestScope.startPage}" />
<c:set var="endPage" value="${requestScope.endPage}" />
<body>
<h1>글목록(전체글:${totalRowCount})</h1>
<table border="1">
    <tr>
        <th>번호</th><th>레스토랑명</th><th>주소</th><th>전화번호</th>
        <th>작성일</th><th>관심가게</th><th>평점</th>
    </tr>
    <c:choose>
    <c:when test="${totalRowCount gt 0}">
       <% SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일"); %>
       <c:forEach var="restaurant" items="${restaurants}">
           <% Timestamp timestamp = ((Restaurant) pageContext.getAttribute("restaurant")).getReg_date(); %>
           <% Date date = new Date(timestamp.getTime()); %>
           <tr>
               <td>${restaurant.num}</td>
               <td width="250">
                   <a href="detail?num=${restaurant.num}&pageNum=${pageNum}">${restaurant.name}</a>
               </td>
               <td>${restaurant.address}</td>
               <td>${restaurant.tel}</td>
               <td><%=sdf.format(date) %></td>
               <td>${restaurant.likecount}</td>
			   <td>${restaurant.score}</td>
           </tr>
       </c:forEach>
    </c:when>
    <c:otherwise>
        <tr>
            <td colspan="7">등록된 레스토랑 없음</td>
        </tr>
    </c:otherwise>
    </c:choose>
</table>

<c:if test="${totalRowCount gt 0}">
    <%-- [이전] --%>
    <c:if test="${startPage gt pageBlock}">
        <a href="list?pageNum=${startPage - pageBlock}">[이전]</a>
    </c:if>
    <%-- 1~10 페이지블록 범위 출력 --%>
    <c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
        <c:choose>
        <c:when test="${i eq pageNum}">
            <a href="list?pageNum=${i}"><b> [${i}] </b></a>
        </c:when>
        <c:otherwise>
            <a href="list?pageNum=${i}"> [${i}] </a>
        </c:otherwise>
        </c:choose>
    </c:forEach>
    <%-- [다음] --%>
    <c:if test="${endPage lt pageCount}">
        <a href="list?pageNum=${startPage + pageBlock}">[다음]</a>
    </c:if>
</c:if>
</body>
</html>





