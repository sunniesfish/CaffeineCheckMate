<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/resources/css/updateTEL.css"rel="stylesheet" type="text/css">
</head>
<body>
<br/><br/><br/>
<div class="container">
	<% session.removeAttribute("reWindow"); %>
	<%
		if(session.getAttribute("editOK") != null && session.getAttribute("editCancel")==null){
			session.removeAttribute("editOK");
			session.setAttribute("reWindow","reWindow");%>
		<h1>변경이 완료되었습니다.</h1>
		<input type="button" value="확인" onclick="self.close()">
	<%}
		if(session.getAttribute("editCancel") != null && session.getAttribute("editOK")==null){
		session.removeAttribute("editCancel");
		session.setAttribute("reWindow","reWindow");
	%>
		<h1>변경이 실패하였습니다.</h1>
		<input type="button" value="확인" onclick="self.close()">
	<%}%>
	
	<%if(session.getAttribute("reWindow") == null){ %>
	<h1>전화번호변경</h1>
		<form action="/UpdateMemberHandler.do" method="get" name="reUpdateTel" id="reUpdateTel">
			tel<br/><input type="text" name="updateTel1" id="updateTel1" placeholder="010" maxlength=3  minlength=3 size=3 required="required"/>-
					<input type="text" name="updateTel2" id="updateTel2" placeholder="0000" maxlength=4  minlength=4 size=4 required="required"/>-
					<input type="text" name="updateTel3" id="updateTel3" placeholder="0000" maxlength=4  minlength=4 size=4 required="required"/><br/>
					<input type="hidden" value="UpdateTel" name="Value">
					<br/><input type="submit" value="수정"/>
					<input type="button" value="취소" onclick="self.close()"/>
		</form>
	<%} %>
</div>
</body>
</html>