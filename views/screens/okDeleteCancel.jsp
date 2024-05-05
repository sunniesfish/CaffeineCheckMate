<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>탈퇴취소페이지</title>
<style>
/* 전체 body 스타일 */
body {
    font-family: 'Noto Sans KR', sans-serif; /* 폰트 설정 */
    background-color: #E1DDDB; /* 배경색 설정 */
    display: flex; /* Flexbox 레이아웃 사용 */
    flex-direction: column; /* 세로 방향 정렬 */
    justify-content: center; /* 가로 중앙 정렬 */
    align-items: center; /* 세로 중앙 정렬 */
    height: 100vh; /* 뷰포트 높이에 맞게 설정 */
}

/* 제목 스타일 */
h1,h3 {
    color: #EF6C33; /* 글자색 설정 */
    margin-bottom: 20px; /* 하단 여백 설정 */
    text-align: center; /* 가운데 정렬 */
}

/* 버튼 스타일 */
form {
    display: flex;
    justify-content: center;
    gap: 10px;
}

form input[type="submit"], form input[type="button"] {
    width: 200px; /* 버튼 너비 설정 */
    padding: 10px; /* 안쪽 여백 설정 */
    border: none; /* 테두리 없애기 */
    border-radius: 5px; /* 테두리 반경 설정 */
    background-color: #0C4A60; /* 배경색 설정 */
    color: white; /* 글자색 설정 */
    cursor: pointer; /* 커서 설정 */
}

/* 버튼 호버 효과 */
form input[type="submit"]:hover, form input[type="button"]:hover {
    background-color: #ABDFF1; /* 호버 시 배경색 변경 */
}
</style>
</head>
<body>
<div>
	<h1>탈퇴가 취소 되었습니다 </h1>
	<br/>
	<h3>다시 정상적인 회원활동이 가능합니다.</h3>
</div>
		<form action="<%= request.getContextPath()%>/views/screens/Main.jsp" name="loginFrm" method="post">
			<input type="submit" value="홈으로" class="login-button"/>
		</form>
</body>
</html>