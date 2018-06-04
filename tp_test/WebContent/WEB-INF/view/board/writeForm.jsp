<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
	function check() {
		//if (document.fr.name.value == '') {}
		if (document.fr.name.value.length == 0) {
			alert('글쓴이를 입력하세요');
			document.fr.name.focus();
			return false;
		}
		if (document.fr.passwd.value.length == 0) {
			alert('패스워드를 입력하세요');
			document.fr.passwd.focus();
			return false;
		}
		if (document.fr.subject.value.length == 0) {
			alert('제목을 입력하세요');
			document.fr.subject.focus();
			return false;
		}
		if (document.fr.content.value.length == 0) {
			alert('내용을 입력하세요');
			document.fr.content.focus();
			return false;
		}
	}
</script>
</head>
<body>
<h1>게시판 글쓰기</h1>
<form action="write" method="post" name="fr" onsubmit="return check()" enctype="multipart/form-data">
	<table border="1">
		<tr>
			<th>글쓴이</th>
			<td><input type="text" name="name"></td>
		</tr>
		<tr>
			<th>패스워드</th>
			<td><input type="password" name="passwd"></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="subject"></td>
		</tr>
		<tr>
            <th>파일</th>
            <td><input type="file" name="file"></td>
        </tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="13" cols="40" name="content"></textarea> </td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="글쓰기">
				<input type="reset" value="다시쓰기">
				<input type="button" value="글목록" onclick="location.href='list?pageNum=${param.pageNum}'">
			</td>
		</tr>
	</table>
</form>
</body>
</html>






