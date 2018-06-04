<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>레스토랑 등록</title>
</head>
<body>
	<h1>레스토랑 등록</h1>
	<form action="add" method="post" name="frm" enctype="multipart/form-data">
<table>
<tr><th>레스토랑명</th>
	<td><input type="text" name="name"></td><tr>
<tr><th>주소</th>
    <td><input type="text" name="address"></td><tr>
<tr><th>전화번호</th>
    <td><input type="text" name="tel"></td><tr>
<tr><th>파일</th>
    <td><input type="file" name="file"></td><tr>  

</table>
<input type="submit" value="등록" class="btn">
<input type="reset" value="재작성" class="btn">
</form>

</body>
</html>