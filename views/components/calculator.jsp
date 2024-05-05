<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="global.dto.Main" %>
<%@ page import="favorite.dto.Favorite" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<!-- <%
	boolean isAuth = request.getSession().getAttribute("AUTH_USER_ID") != null ? true:false;
	pageContext.setAttribute("isAuth", isAuth);
	%> -->
	<link href="https://fonts.googleapis.com/css?family=Raleway:400,300,600,800,900" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/progressbar.js@1.1.1/local-dev/main.min.css">
	<meta charset="UTF-8">
	<meta http-equiv="Compatible" content="no-cache"/>
	<title>Insert title here</title>
</head>
<body>
    <div class="calc-box">
        <div class="calc-box__column-first">
            <div class="calc-container"></div>
        </div>
        <div class="calc-box__column-second">
            <div class="calc-box__result"><span class="calc-box__result-title">Caffeine</span></div>
            <div class="calc-box__dailyc">${main.calculationResult} <span>mg</span> </div>
            <div class="calc-box__reset-box">
                <button id="calc-box__btn-reset" class="clickable">Reset</button>
            </div>
        </div>
    </div>
</body>
<!--즐겨찾기js가 계산기js보다 위에 있어야함, 즐겨찾기js는 type="text/babel"-->
<!-- <script>
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
<script src="/resources/js/calculator.js"></script> -->
</html>