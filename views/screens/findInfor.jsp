<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일인증</title>
<style>
/* 전체 body 스타일 */
body {
    font-family: 'Noto Sans KR', sans-serif; /* 폰트 설정 */
    background-color: #f5f6f7; /* 배경색 설정 */
    display: flex; /* Flexbox 레이아웃 사용 */
    justify-content: center; /* 가로 중앙 정렬 */
    align-items: center; /* 세로 중앙 정렬 */
    height: 100vh; /* 뷰포트 높이에 맞게 설정 */
}

/* 로그인 컨테이너 스타일 */
.container {
    width: 400px; /* 너비 설정 */
    background-color: #fff; /* 배경색 설정 */
    padding: 20px; /* 안쪽 여백 설정 */
    border-radius: 10px; /* 테두리 반경 설정 */
    box-shadow: 0 5px 20px rgba(0, 0, 0, 0.1); /* 그림자 효과 설정 */
}

/* 제목 스타일 */
h3 {
    color: #03c75a; /* 글자색 설정 */
    margin-bottom: 20px; /* 하단 여백 설정 */
    text-align: center; /* 가운데 정렬 */
}

/* 입력 필드 스타일 */
input[type="text"], input[type="password"] {
    width: 30%; /* 너비 100% 설정 */
    padding: 10px; /* 안쪽 여백 설정 */
    margin: 10px 0; /* 바깥 여백 설정 */
    border: 1px solid #ddd; /* 테두리 설정 */
    border-radius: 5px; /* 테두리 반경 설정 */
    box-sizing: border-box; /* 상자 크기 설정 */
}

/* 버튼 스타일 */
input[type="submit"], input[type="button"] {
    width: 30%; /* 너비 100% 설정 */
    padding: 10px; /* 안쪽 여백 설정 */
    margin: 10px 0; /* 바깥 여백 설정 */
    border: none; /* 테두리 없애기 */
    border-radius: 5px; /* 테두리 반경 설정 */
    background-color:  #03c75a; /* 배경색 설정 */
    color: white;/*  글자색 설정 */
    cursor: pointer; /* 커서 설정 */
}

/* 버튼 호버 효과 */
input[type="submit"]:hover, input[type="button"]:hover {
    background-color: #029e47; /* 호버 시 배경색 변경 */
}
.container{
text-align: center;
}
.button-container {
    display: flex;
    justify-content: center; /* 버튼을 가운데 정렬 */
    gap: 10px; /* 버튼 사이의 간격을 10px로 설정 */
}
</style>
</head>
<body>

<div class="container">
</br></br></br>
<%
String value = request.getParameter("findID");
if(value.equals("findID")){%>
		<h3>이메일인증</h3>
	<form action="/MailCheckHandler.do" name= "Mail2" id = "Mail2" method="post">
	  <input type="text" name="input">
	  <input type="hidden" name="hidden" value="emailCheck">
	  <input type="submit" value="인증">
	</form><% 
			}else{ %>
					
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
		 	<input type="submit" value="메일보내기" />
			<input type="submit" value="취소" onclick="self.close();"/>

		</form>
<%}%>

</div>
</body>
</html>