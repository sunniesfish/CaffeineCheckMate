<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일인증</title>
<link href="/resources/css/joinEmail.css"rel="stylesheet" type="text/css">
</head>
<body>
	<div class="container">
		<br/><br/><br/>
				<%String hidden = (String)session.getAttribute("code");
				  String find = (String)session.getAttribute("find");
				  System.out.println(find);
				  System.out.println(hidden);
				if(hidden != null){%>
				<h3>이메일인증</h3>
			<form action="/MailCheckHandler.do" name= "Mail2" id = "Mail2" method="post">
			  <input type="text" name="input">
			  		<%if(find == null){ %>
					<input type="hidden" value="emailCheck" name="hidden" >
					<%}else if (find.equals("findID")){ %>
					<input type="hidden" value="findID" name="hidden">
					<%}else if(find.equals("findPW")){%>
					<input type="hidden" value="findPW" name="hidden">
	  				<%}%>
			  <input type="submit" value="인증">
			</form><% 
					session.setAttribute("codeMail" , session.getAttribute("code"));
					session.removeAttribute("code"); 
					%>
				<script>
				    // 이메일 인증 성공 시
				    newWindow.onload = function() {
				        // 부모 창의 HTML 요소 업데이트
				        const container = document.querySelector('.container');
				        // 세션 속성 업데이트
				        sessionStorage.setItem('authCodeVerified', 'true');
				        return true; // 트루 값 반환
				    }
				    // 이메일 인증 실패 시
				    newWindow.onerror = function() {
				        // 부모 창의 HTML 요소 업데이트
				        const container = document.querySelector('.container');
				        
				        // 세션 속성 업데이트
				        sessionStorage.setItem('authCodeVerified', 'false');
				        return false; // 폴스 값 반환
				    }
				</script>
		<%}else{ %>
			<form action="/MailCheckHandler.do" name= "Mail" method="post">
				<h3>인증 메일보내기</h3>
				<input type="text" name="joinEmail1" id="joinEmail" size=10 required="required"/> @
				<input type="text" name="joinEmail2" id="domain-txt" size=10 required="required"/>
					<select name="joinEmail2" id="domain-list">
						<option value="type">직접 입력</option>
						<option value="naver.com">naver.com</option>
						<option value="gmail.com">google.com</option>
						<option value="hanmail.net">hanmail.net</option>
						<option value="nate.com">nate.com</option>
						<option value="kakao.com">kakao.com</option>
					</select>
						<script>
							// select 옵션 변경 시
							const domainListEl = document.querySelector('#domain-list')
							const domainInputEl = document.querySelector('#domain-txt')
							domainListEl.addEventListener('change', (event) => {
								// option에 있는 도메인 선택 시
								if(event.target.value !== "type") {
									// 선택한 도메인을 input에 입력하고 disabled
									domainInputEl.value = event.target.value
									domainInputEl.disabled = true
								} else { // 직접 입력 시
									// input 내용 초기화 & 입력 가능하도록 변경
									domainInputEl.value = ""
									domainInputEl.disabled = false
								}
							})
						</script>
				<input type="hidden" value="emailinput" name="hidden">
			 	<input type="submit" value="메일보내기"/>
				<input type="submit" value="취소" onclick="self.close();"/>
			</form>
		<%}%>
	</div>
</body>
</html>