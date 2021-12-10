<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
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
<body>
<h2> findAll</h2>

<c:if test="${!empty sessionScope.loginID }">
	 <a href="/">로그아웃</a>
<!-- 	<form action="logout" method="get">
		<button name="logout">로그아웃</button>
	</form> -->
	<h2>${sessionScope.loginID}님 환영합니다.</h2>
	<a href="/mypage?m_id=${sessionScope.loginID}">마이페이지</a>	
</c:if>

<c:if test="${empty sessionScope.loginID }">
	<a href = "login"> 로그인 </a>
</c:if>

	<form action="/board/search" method="get">
		<select name="searchtype">
			<option value="b_title">제목</option>
			<option value="b_writer">작성자</option> 
		</select>
			<input type="text" name="keyword" placeholder="검색어">
			<input type=submit vlaue ="검색">
		</form>


	<table>
		<tr>
        	<th>넘버</th>
       		<th>제목</th>
       		<th>작성자</th>
       		<th>내용</th>
       		<th>조회수</th>
       		<th>작성시간</th>
       		<th>조회</th>
        </tr>
	<c:forEach items="${board}" var="board">
		<tr>
			<td>${board.b_number}</td>
			<td>${board.b_title}</td>
			<td>${board.b_writer} </td>
			<td>${board.b_contents} </td>
			<td>${board.b_hits} </td>
			<td>${board.b_date} </td>
			<td><a href="/board/view?page=${paging.page}&b_number=${board.b_number}&id=${sessionScope.loginID}">조회</a> </td>
<%-- 			<td><a href="/board/view?b_number=${b.b_number}&page=${paging.page}">${b.b_contentsl}</a></td> --%>
		 </tr> 
		<br>
	</c:forEach>
	</table>
	
		<div>
		<c:choose>				
			<c:when test="${paging.page<=1}"> 
				[이전]&nbsp;
			</c:when>
			<c:otherwise>                     
		
				<a href="paging?page=${paging.page-1}">[이전]</a>&nbsp;
			</c:otherwise>
		</c:choose>
		
		
		<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i" step="1">
			<c:choose>
				<c:when test="${i eq paging.page}">
					${i}
				</c:when>
				<c:otherwise>
					<a href="/board/paging?page=${i}">${i}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	
		<c:choose>
			<c:when test="${paging.page>=paging.maxPage}">
				[다음]
			</c:when>
			<c:otherwise>
				<a href="paging?page=${paging.page+1}">[다음]</a>
			</c:otherwise>
		</c:choose>
	</div>
	
	<form action="/board/paging" method="GET" >
	 페이징 개수 : <select name="pageShow">
            <option value =5>5</option>
            <option value =10>10</option>
        </select> <br>
        <input type="submit" value="수정">
     </form>  
     
	<a href ="/board/save/"> 게시글 작성 </a>
</body>
</html>