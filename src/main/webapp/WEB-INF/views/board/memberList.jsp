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
<body>
	<h2>memberList</h2>
	
	
	<table>
		<tr>
        	<th>회원 번호</th>
       		<th>아이디</th>
       		<th>비밀번호</th>
       		<th>이름</th>
       		<th>이메일</th>
       		<th>전화번호</th>
       		<th>프로필 사진</th>
       		<th>삭제</th>
        </tr>
		<c:forEach items="${member}" var="member">
		<tr>
			<td>${member.m_number}</td>
			<td>${member.m_id}</td>
			<td>${member.m_password} </td>
			<td>${member.m_name} </td>
			<td>${member.m_mail} </td>
			<td>${member.m_phone} </td>
			<td><img alt="" src="/resources/upload/${member.m_filename}"> </td>
			<td><a href="/board/memberList/delete?m_number=${member.m_number}">삭제</a> </td>
		 </tr> 
		<br>
		</c:forEach>
	</table>
	
</body>
</html>