<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보확인</title>
<link href="/resources/css/identify.css"rel="stylesheet" type="text/css">
</head>
<body>
	<div class="container">
	 	<%
			String value = request.getParameter("Value");
			if(value.equals("delete")){
		%>	
			<form action="<%=request.getContextPath() %>/DeleteMemberHandler.do" method = "post" name="DeleteLogin" id="DeleteLogin">
						<h1>로그인정보확인</h1>
				아이디<br/><input type="text" name="identgyId" id="deleteId" placeholder="아이디" required="required"/><br/>
				비밀번호<br/><input type="password" name="identgyPw" id="deletePw" placeholder="비밀번호" required="required"/><br/>
							<input type="hidden" value="delete" name="Value"/>
						<br/><input type="submit" value="탈퇴"/>
							<input type="button"  onclick="location.href='/main.do'" value="취소"/>
			</form>
		<%}if(value.equals("edit")){%>
			<form action="<%=request.getContextPath() %>/UpdateMemberHandler.do" method = "post" name="UpdateLogin" id="UpdateLogin">
						<h1>로그인정보확인</h1>
					아이디<br/><input type="text" name="identgyId" id="updateId" placeholder="아이디" required="required"/><br/>
					비밀번호<br/><input type="password" name="identgyPw" id="updatePw" placeholder="비밀번호" required="required"/><br/>
							<br/><input type ="hidden" value="MyselfCheck" name="Value">
						<input type="submit" value="정보확인"/>
						<input type="button"  onclick="location.href='/main.do'" value="취소"/>
			</form>
		<%}if(value.equals("deleteCancel")){%>	
			<form action="<%=request.getContextPath() %>/DeleteMemberHandler.do" method = "post" name="DeleteCancelLogin" id="DeleteCancelLogin">
						<h1>로그인정보확인</h1>
					아이디<br/><input type="text" name="identgyId" id="deleteCancelId" placeholder="아이디" required="required"/><br/>
					비밀번호<br/><input type="password" name="identgyPw" id="deleteCancelPw" placeholder="비밀번호" required="required"/>
								<input type="hidden" value="deleteCancel" name="Value"/><br/>
						<br/><input type="submit" value="정보확인"/>
							<input type="button"  onclick="location.href='/main.do'" value="취소"/>
			</form>
		<%}%>
		<%// 로그인 실패시 에러메세지 관련 부분
		String model = (String) session.getAttribute("errMSG");
		if(model != null){
			out.println("<br/>" + model);
			session.removeAttribute("errMSG");
		}%>
	</div>
</body>
</html>