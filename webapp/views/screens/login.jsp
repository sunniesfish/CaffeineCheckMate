<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" href="data:,">
<meta charset="UTF-8">
<title>로그인</title>
<link href="/resources/css/login.css"rel="stylesheet" type="text/css">
</head>
<body>
<div class="container">
<!-- 아이디저장 jq -->
<script type="text/javascript" src="https://code.jquery.com/jquery-1.10.2.min.js"/></script>

<script>
	$(document).ready(function(){
		var key = getCookie("idChk"); //user1
		if(key!=""){
			$("#loginId").val(key); 
		}
		 
		if($("#loginId").val() != ""){ 
			$("#idSaveCheck").attr("checked", true); 
		}
		 
		$("#idSaveCheck").change(function(){ 
			if($("#idSaveCheck").is(":checked")){ 
				setCookie("idChk", $("#loginId").val(), 7); 
			}else{ 
				deleteCookie("idChk");
			}
		});
		 
		$("#loginId").keyup(function(){ 
			if($("#idSaveCheck").is(":checked")){
				setCookie("idChk", $("#loginId").val(), 7); 
			}
		});
	});
	function setCookie(cookieName, value, exdays){
	    var exdate = new Date();
	    exdate.setDate(exdate.getDate() + exdays);
	    var cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" + exdate.toGMTString());
	    document.cookie = cookieName + "=" + cookieValue;
	}
	 
	function deleteCookie(cookieName){
		var expireDate = new Date();
		expireDate.setDate(expireDate.getDate() - 1);
		document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();
	}
		 
	function getCookie(cookieName) {
		cookieName = cookieName + '=';
		var cookieData = document.cookie;
		var start = cookieData.indexOf(cookieName);
		var cookieValue = '';
		if(start != -1){
			start += cookieName.length;
			var end = cookieData.indexOf(';', start);
			if(end == -1)end = cookieData.length;
			cookieValue = cookieData.substring(start, end);
		}
		return unescape(cookieValue);
	}
</script>

	<form action="<%=request.getContextPath() %>/LoginHandler.do" method = "post" name="loginForm" id="loginForm">
			<img class="logo" src="/resources/imgs/logo.png" alt="logo" onclick="window.location.href='/main.do'"/>
			<br/>
			<div class="icon-input-container">
			<img src="/resources/imgs/id.png" alt="id" width="20" height="20">
			<input type="text" name="loginId" id="loginId" placeholder="아이디" required="required"/><br/>
			</div>
			<div class="icon-input-container">
			<img src="/resources/imgs/password.png" alt="password" width="20" height="20">
			<input type="password" name="loginPw" id="loginPw" placeholder="비밀번호" required="required"/>
			</div><br/>
 						<input type="checkbox" name="remember" id="idSaveCheck"/> 아이디저장 |
 						<a href="<%= request.getContextPath()%>/views/screens/findUser.jsp?find=ID">&nbsp;아이디찾기&nbsp;</a>&nbsp;|
 						<a href="<%= request.getContextPath()%>/views/screens/findUser.jsp?find=PW">&nbsp;비밀번호찾기</a>
 						<input type="hidden" value="loginValue" name="loginValue">
			<br/><input type="submit" value="로그인"/>
				<input type="button"  onclick="location.href='joinRequest.jsp'" value="회원가입"/>
	</form>
				<%// 로그인 실패시 에러메세지 관련 부분
			String model = (String) session.getAttribute("errMSG");
			if(model != null){
				
				out.println("<br/>" + model);
				session.invalidate();
			}
			%>
</div>

</body>
</html>