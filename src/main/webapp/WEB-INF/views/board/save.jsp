<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>board/save</h2> ${sessionScope.loginID}
<form action="/board/save" method="POST" enctype="multipart/form-data">
		작성자 : <input type="text" name="b_writer" value=${sessionScope.loginID} readonly>
		제목 : <input type="text" name="b_title">
		내용 : <textarea name="b_contents" rows="5"></textarea>
		파일 : <input type="file" name="b_file"><br>
		<input type="submit" value="글쓰기">
	</form>
</body>
</html>