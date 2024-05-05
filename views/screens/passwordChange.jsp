<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호변경</title>
<link href="/resources/css/passwordChange.css"rel="stylesheet" type="text/css">
</head>
<body>

<div class="container">
	<h1>비밀번호 변경</h1>
		<form action="/UpdateMemberHandler.do" method="post">
			새비밀번호: <input type="password" name="updatePw0" id="updatePw0" placeholder="기존비밀번호" required="required"/><br/>
			비밀번호:<input type="password" name="updatePw1" id="updatePw1" placeholder="새비밀번호" required="required"/><br/>
			비밀번호확인:<input type="password" name="updatePw2" id="updatePw2" placeholder="비밀번호확인" required="required"/><br/>
				<input type="hidden" value="passwordChange" name="Value">
				<input type="submit" value="변경"/>
				<input type="button" value="취소" onclick="location.href='/views/screens/updateRequest.jsp'"/>
		</form>
</div>
</body>
</html>