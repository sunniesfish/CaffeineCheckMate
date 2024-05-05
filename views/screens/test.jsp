<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="global.dto.Main" %>
<%@ page import="favorite.dto.Favorite" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

	<%
	boolean isAuth = request.getSession().getAttribute("AUTH_USER_ID") != null ? true:false;
	pageContext.setAttribute("isAuth", isAuth);
	%>

	

	<link href="https://fonts.googleapis.com/css?family=Raleway:400,300,600,800,900" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/progressbar.js@1.1.1/local-dev/main.min.css">
	<meta charset="UTF-8">
	<meta http-equiv="Compatible" content="no-cache"/>
	<title>Insert title here</title>
</head>
<body>

	<a href="/addfav.do?C_NO=2">C_NO=2 즐겨찾기 추가</a>
	<a href="/deletefav.do?C_NO=1">C_NO=1 즐겨찾기 삭제</a>
	<a href="/calculate.do?C_NO=1">C_NO=1 카페인계산</a>
	<a href="/calculate.do?C_NO=2">C_NO=2 카페인계산</a>
	<a href="/reset.do">카페인양 리셋</a>
	<br/>
	<br/>
	<h2>임시 커피목록</h2>
	<div class="coffee-box">
		<div class="coffeelist-item" value="C_NO=2"><h4 style="display: inline;">카페라떼</h4><button style="display: inline-block;" class="addFav-btn">+</button></div>
		<div class="coffeelist-item" value="C_NO=1"><h4 style="display: inline;">아메리카노</h4><button style="display: inline-block;" class="addFav-btn">+</button></div>
		<div class="coffeelist-item" value="C_NO=3"><h4 style="display: inline;">그린티</h4><button style="display: inline-block;" class="addFav-btn">+</button></div>
		<div class="coffeelist-item" value="C_NO=4"><h4 style="display: inline;">밀크티</h4><button style="display: inline-block;" class="addFav-btn">+</button></div>
		<div class="coffeelist-item" value="C_NO=5"><h4 style="display: inline;">아이스아메리카노</h4><button style="display: inline-block;" class="addFav-btn">+</button></div>
	</div>
	<br/>
	<br/>
	<h2>calc-box</h2><br/>
	<div>
		
	</div>
	<div class="calc-container" style="height: 100px; width: 130px;"></div>
	<br/>
	<br/>
	<h2>fav-box</h2>
	<c:choose>
		<c:when test="${isAuth}">
			<h2>즐겨찾기목록</h2>
		</c:when>
		<c:when test="${!isAuth}">
			<h2>즐겨찾기 많은 커피 5개</h2>
		</c:when>
	</c:choose>
	<div class="fav-pop-box">
		<c:choose>
			<c:when test="${isAuth}">
				<div class="fav-box">
					<c:forEach var="favorite" items="${main.favorites}">
						<div class="fp-item" id="fi_${favorite.key}" value="C_NO=${favorite.key}">
							<div class="fp-item__box">
								<div class="fp-item__img">이미지 이름 : ${favorite.value.c_IMAGE}</div>
								<div class="fp-item__info">커피 이름 : ${favorite.value.c_NAME}</div>
							</div>
							<button class="fp-item__delete-btn"><i class="fa-solid fa-x"></i></button>
						</div>
					</c:forEach>
				</div>
			</c:when>
			<c:when test="${!isAuth}">
				<div class="popular-box">
					<c:forEach var="popular" items="${main.favorites}">
						<div class="fp-item" id="fi_${popular.key}" value="${popular.value.c_CAFFEINE}">
							<div class="fp-item__box">
								<div class="fp-item__img">이미지 이름 : ${popular.value.c_IMAGE}</div>
								<div class="fp-item__info">커피 이름 : ${popular.value.c_NAME}</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</c:when>
		</c:choose>
    </div>
</body>
<!--즐겨찾기js가 계산기js보다 위에 있어야함, 즐겨찾기js는 type="text/babel"-->
<script>
	let calcResult = "${main.calculationResult}";
	const isAuth = "${isAuth}"==="true"? true : false;
	console.log("html : ",isAuth);
</script>

<script src="https://unpkg.com/react@17.0.2/umd/react.production.min.js"></script>
<script src="https://unpkg.com/react-dom@17.0.2/umd/react-dom.production.min.js"></script> 
<script src="https://unpkg.com/@babel/standalone/babel.min.js"></script> 

<script src="https://kit.fontawesome.com/9e2cfcdf3a.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/progressbar.js@1.1.1/dist/progressbar.min.js" integrity="sha256-CjGwkk3nsu5BkdGgSjediSja+n8zB6HARhF/eZxtO0g=" crossorigin="anonymous"></script>

<script src="/resources/js/favoritelist.js" type="text/babel"></script>
<script src="/resources/js/calculator.js"></script>
</html>