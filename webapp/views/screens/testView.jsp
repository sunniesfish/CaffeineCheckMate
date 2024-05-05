<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>임시메인페이지</title>
<style>
.container {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    width: 100%;
    margin-top: 20px;
    text-align: center;
}
/* 기존 버튼 스타일 */
input[type="button"], input[type="submit"] {
    padding: 8px 15px; /* 패딩 조정 */
    margin-top: 10px; /* 상단 마진 추가 */
    background-color: #4CAF50;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    width: 30%; /* 버튼 너비 조정 */
}
/* 버튼 및 서브밋 스타일 조정 */
input[type="button"], input[type="submit"] {
    padding: 8px 15px; /* 패딩 조정 */
    margin-top: 10px; /* 상단 마진 추가 */
    background-color: #4CAF50; /* 배경색 */
    color: white; /* 글자색 */
    border: none; /* 테두리 제거 */
    border-radius: 4px; /* 모서리 둥글게 */
    cursor: pointer; /* 커서 모양 변경 */
    width: 100%; /* 너비 조정 */
}

input[type="button"]:hover, input[type="submit"]:hover {
    background-color: #45a049; /* 호버 시 배경색 변경 */
}
.button-container {
    display: flex;
    justify-content: center; /* 버튼들을 가운데 정렬 */
    gap: 10px; /* 버튼 사이의 간격 조정 */
}
</style>
</head>
<body>
<button onclick="location.href='/CustomBoardListHandler.do'">CustomBoardListHandler.do</button>

<button onclick="location.href='/views/screens/test.jsp'">test.jsp 즐겨찾기</button>

<button onclick="location.href='/main.do'">계산기/즐겨찾기</button>


<%
	if(session.getAttribute("AUTH_USER_ID") != null){
%>
	<div class="container">
	<h1>다돌고  결과 viewPage 돌아오기 완료됨</h1>
	  <strong><%= session.getAttribute("AUTH_USER_NICKNAME") %></strong> 님 안녕하세요
		<form action="<%=request.getContextPath() %>/Logout">
			<input type="submit" value="로그아웃"  class="login-button" onclick="alert('로그아웃되었습니다.')"/>
		</form>
		<form action="<%=request.getContextPath() %>/views/screens/identify.jsp">
			<input type="hidden" value="edit" name="Value"/>
			<input type="submit" value="정보수정"/>
		</form>
		<!-- <a href="<%=request.getContextPath() %>/views/screens/identify.jsp?Value=edit">정보수정</a> -->
		<!-- 마이페이지 버튼 추가 -->
		<form action="/mypage.do">
			<input type="submit" value="My Page" />
		</form>
	</div>
	<%}else{%>
	<div class="container">
	<h1>임시 메인페이지</h1>
		<form action="<%= request.getContextPath()%>/views/screens/login.jsp" name="loginFrm" method="post">
			<input type="submit" value="로그인" class="login-button"/>
			
		</form>
	</div>
<%}%>
	<h1>커피리스트</h1>
	<form action="/coffeeList.do" method="get">
		<input type="submit" value="coffeelist">
	</form>
</body>
</html>