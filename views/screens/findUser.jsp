<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" href="data:,">
<meta charset="UTF-8">
<title>아이디찾기</title>
<link href="/resources/css/login.css"rel="stylesheet" type="text/css">
</head>
<body>
	<div class="container">
	<%
	String value = request.getParameter("find");
	String result = request.getParameter("result");
	
	if(value.equals("ID")){
	session.setAttribute("find","findID");
	%>
					<h1>아이디찾기</h1>
	    			<input type="button" value="메일인증" onclick="joinEmail()"/><br/>
	    			<input type="button" value="로그인" onclick="location.href='/views/screens/login.jsp'"/><br/>
	    			<input type="button" value="홈으로" onclick="location.href='/main.do'"/><br/>
	    		<script>
		    		var emailChecked = false; // 이메일 인증 여부를 저장하는 변수
		
		    		function joinEmail() {
		    		    var url = "/views/screens/joinEmail.jsp?find=findID";
		    		    var newWindow = window.open(url, "_blank_1", "width=500,height=300,toolbar=no,menubar=no,resizable=no,scrollbars=yes");
		    		    window.emailChecked = true;
		    		}
	    		</script>
	<%}else if(value.equals("PW")){ 
		session.setAttribute("find","findPW"); 
	%>
					<h1>비밀번호찾기</h1>
	    			<input type="button" value="메일인증" onclick="joinEmail()"/><br/>
					<input type="button" value="로그인" onclick="location.href='/views/screens/login.jsp'"/><br/>	    		
					<input type="button" value="홈으로" onclick="location.href='/main.do'"/><br/>				
						<script>
							var emailChecked = false; // 이메일 인증 여부를 저장하는 변수
						
							function joinEmail() {
							    var url = "/views/screens/joinEmail.jsp?find=findPW";
							    var newWindow = window.open(url, "_blank_1", "width=500,height=300,toolbar=no,menubar=no,resizable=no,scrollbars=yes");
						
							    // 자식 창에서 인증 성공 시 부모 창에 메시지 전송
							    newWindow.addEventListener("message", function(event) {
							        if (event.data === "email_verified") {
							            emailChecked = true;
							            alert("이메일 인증 성공!");
							        } else if (event.data === "email_verification_failed") {
							            emailChecked = false;
							            alert("이메일 인증 실패!");
							        }
							    });
							}
						</script>
			<%}else if(value.equals("REID")){ %>
				<h1>회원아이디:</h1>
				<h2><%= result %></h2>
				<input type="button" value="닫기" onclick="self.close();">
				<input type="button" value="로그인" onclick="self.close(); window.opener.location.href = '/views/screens/login.jsp';">
			<%}else if(value.equals("REPW")){%>
				<form action="/UpdateMemberHandler.do" method="post" name="findChangePassword">
					<h1>비밀번호를 변경해 주세요</h1>
					<input type="hidden" value="<%= result %>" name="findId">
					<input type="hidden" value="findPassword" name="Value">
						변경비밀번호<input type="password" name="findPassword1" id="findPassword1"><br/>
						비밀번호확인<input type="password" name="findPassword2" id="findPassword2">
					<input type="submit" value="변경">
					<input type="submit" value="취소" onclick="self.close();">
				</form>
			<%}%>
				
	</div>

</body>
</html>