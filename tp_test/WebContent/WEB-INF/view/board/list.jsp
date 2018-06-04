<%@page import="com.test.domain.Board"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
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
    <c:redirect url="/app/member/login"/>
</c:if>
<%-- request 속성값 가져오기 --%>
<c:set var="totalRowCount" value="${requestScope.totalRowCount}" />
<c:set var="pageNum" value="${requestScope.pageNum}" />
<c:set var="boards" value="${requestScope.boards}" />
<c:set var="pageCount" value="${requestScope.pageCount}" />
<c:set var="pageBlock" value="${requestScope.pageBlock}" />
<c:set var="startPage" value="${requestScope.startPage}" />
<c:set var="endPage" value="${requestScope.endPage}" />
<body>
<h1>글목록(전체글:${totalRowCount})</h1>
<h3><a href="write?pageNum=${pageNum}">글쓰기</a></h3>
<table border="1">
    <tr>
        <th>번호</th><th>제목</th><th>작성자</th>
        <th>작성일</th><th>조회수</th><th>IP</th>
    </tr>
    <c:choose>
    <c:when test="${totalRowCount gt 0}">
       <% SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일"); %>
       <c:forEach var="board" items="${boards}">
           <% Timestamp timestamp = ((Board) pageContext.getAttribute("board")).getReg_date(); %>
           <% Date date = new Date(timestamp.getTime()); %>
           <tr>
               <td>${board.num}</td>
               <td width="250">
                   <c:if test="${board.re_lev gt 0}">
                       <c:set var="wid" value="${board.re_lev * 15}" />
                       <img src="../../img/level.gif" width="${wid}" height="10">
                       <img src="../../img/re.gif">
                   </c:if>
                   <a href="detail?num=${board.num}&pageNum=${pageNum}">${board.subject}</a>
               </td>
               <td>${board.name}</td>
               <td><%=sdf.format(date) %></td>
               <td>${board.readcount}</td>
               <td>${board.ip}</td>
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





