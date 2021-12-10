<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
       table, th,tr,td{
            border: 1px solid black;           
        	border-collapse: collapse;
        }
</style>
</head>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<body>
	로그인 아이디 : ${sessionScope.loginID}<br>
	작성자 : ${board.b_writer} 
	글 번호 : ${board.b_number} 
	제목 : ${board.b_title} 
	내용 : ${board.b_contents} 
	조회수 : ${board.b_hits} 
	작성일자 : ${board.b_date}  
	파일명 : ${board.b_filename} <br>
	<img alt="" src="/resources/upload/board/${board.b_filename}"> <br>
	
	
	<c:if test="${sessionScope.loginID == board.b_writer}">
	로그인 ID : ${sessionScope.loginID}<br>
	작성자 ID : ${board.b_writer}<br>
		<a href="/board/delete?b_number=${board.b_number}">삭제</a> 
	  	<a href="/board/update?b_number=${board.b_number}&page=${page}">수정</a>
	</c:if> 
	
	<c:if test="${sessionScope.loginID eq 'admin'}">
		<br>관리자 권한<br>	
		<a href="/board/delete?b_number=${board.b_number}">삭제</a> 
	</c:if> 
	
	<!-- 댓글 작성 -->
	<div id="comment-write">
	 	<%-- <input type="hidden" id="c_writer" value="${board.b_number}" >  --%>
		<input type="text" id="c_content" placeholder="댓글내용">
		<button id="comment-write-btn">댓글등록</button>	
	</div>
	
	<!-- 댓글목록출력 -->
	<div id="comment-list">
	<table class="table">
		<tr>
		
			<th>댓글번호</th>
			<th>작성자</th>
			<th>내용</th>
			<th>작성시간</th>
			<th>삭제</th>
		
		</tr>
		<c:forEach items="${commentList}" var="comment">
			<tr>		
			<td>${comment.c_number}</td>
			<td>${comment.c_writer}</td>
			<td>${comment.c_contents}</td>
			<td>${comment.c_date}</td>
			</tr>		
		</c:forEach>
	</table>
	</div>

	
</body>
<script>


		
 	const commentBtn =document.getElementById("comment-write-btn");
 	commentBtn.addEventListener("click", function() {
		
		const c_writer=document = ${sessionScope.loginID}
		const c_content=document = $("#c_content").val();
		const b_number = '${board.b_number}';
		
		console.log(c_writer);
		console.log(c_content);
		console.log(b_number);
		
		$.ajax({
			type: 'post',
			url : '/board/comment/save',
			data:{'c_writer':c_writer,'c_contents':c_content,'b_number':b_number},
			dataType:'json', 
			success:function(result){
				console.log(result);
				let output = "<table class='table'>";
				output += "<tr><th>댓글번호ajax</th>";
				output += "<th>작성자ajax</th>";
				output += "<th>내용ajax</th>";
				output += "<th>작성시간ajax</th></tr>";
				for(let i in result){
					output += "<tr>";
					output += "<td>"+result[i].c_number+"</td>";
					output += "<td>"+result[i].c_writer+"</td>";
					output += "<td>"+result[i].c_contents+"</td>";
					output += "<td>"+result[i].c_date+"</td>";
					output += "</tr>";
				}
				output += "</table>";
				// id가 comment-list인 div에 출력
				document.getElementById('comment-list').innerHTML = output;
				// 댓글 입력창을 비움. 
				/* document.getElementById('c_writer').value=''; */
				document.getElementById('c_content').value='';
			},
			error:function(){
				console.log("오류오류")
			}
			
		});			
 	});	
 			
</script>
</html>