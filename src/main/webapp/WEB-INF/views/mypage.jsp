<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<script>
	function update(){

		const idValue=document.getElementById("m_password").value;
		
		if(idValue==${member.m_password }){
	
				update_form.submit();						
		}
		else{			
		 alert("다시입력하세요");
		}		
	}
</script>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>mypage</h2>
	
<form action="memberUpdate" method="POST"  name="update_form" enctype="multipart/form-data">
		회원번호 : <input type="text" name="m_number" value="${member.m_number }" readonly>
		아이디 : <input type="text" name="m_id" value="${member.m_id }" readonly>
		비밀번호 : <input type="text" name="m_password" id="m_password" >
		이름 : <input type="text" name="m_name" value="${member.m_name }" >
		메일 : <input type="text" name="m_mail" value="${member.m_mail }" >
		전화번호 : <input type="text" name="m_phone" value="${member.m_phone }" >
		파일 : <input type="file" name="m_file"><br>		
		<input type="button" value="수정" onclick="update()">
</form>
	
</body>
</html>