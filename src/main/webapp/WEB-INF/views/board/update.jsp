<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/board/update" method="POST" enctype="multipart/form-data">
		제목 : <input type="text" name="b_title" value="${board.b_title }" >
		내용 : <textarea name="b_contents" rows="5">${board.b_contents}</textarea>
		파일 : <input type="file" name="b_file"><br>
		
		<input type="hidden" name="b_number" value="${board.b_number }">
		<input type="hidden" name="b_writer" value="${board.b_writer }">
		<input type="hidden" name="b_hits" value="${board.b_hits }">
		<input type="hidden" name="b_date" value="${board.b_date }">
		
		<input type="submit" value="수정">
	</form>

</body>
</html>