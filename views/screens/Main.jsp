<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
<link rel="stylesheet" href="/resources/css/main.css">
<meta charset="UTF-8">
<title>CaffeinCheckMate</title>
</head>

<body id="main">
	<div class="wrapper ">
		<!-- 헤더 -->
		<jsp:include page="/views/components/header.jsp" />
		<div class="main_container">
		
			<!-- 메인페이지 상단 좌측 박스 -->
			<div class="main_col_1_row1">
				<div class="fav_info_box">
					<div class="fav-area">
					<div class="fav-title"></div>
					<button class="fav-btn__go-coffeelist clickable"><i class="fa-solid fa-plus"></i></button>
					<button class="fav-btn__togle-fav-del clickable">Remove</button>
						<!--즐겨찾기 include 부분-->
						<jsp:include page="/views/components/favorites.jsp"></jsp:include>
					</div>
				</div>
			</div>
			
			<!-- 메인페이지 상단 우측 박스 -->
			<div class="main_col_2_row1">
			<div class="main_profile">
				<c:choose>
		            <c:when test="${not empty sessionScope.AUTH_USER_ID}">
			            <div class="main_profile__img_box">
			           		<img class="main_profile__img" src="/resources/profile/${main.userProfileDTO.p_IMG_COPY}" alt=""/>
				        </div>
			            <div class="main_profile__nickname-box">
			            	<span class="main_profile__nickname">닉네임 : ${main.userProfileDTO.m_NICKNAME}</span>
			            	<button class="main_profile__btn">수정</button>
			            </div>
		            </c:when>
	            <c:otherwise>
		            <div>
			            <div class="main_profile_login_box">
			           		<div class="main_profile_login_text">로그인하여 자주 찾는 음료를 즐겨찾기 하고 </div>
			           		<div class="main_profile_login_text">카페인 계산기를 통해 섭취량을 기록하세요!</div>
			           	</div>
			          	<button class="main_profile_login-page-btn" onclick="window.location.href = '/views/screens/login.jsp';">로그인하러 가기</button>
			        </div>
	            </c:otherwise>
	            </c:choose>
			</div>
			</div>
			
			<!-- 메인페이지 하단 박스 카페인 정보 -->
			<div class="main_col_1_row2">
				<div class="caffeine_info_box">
					<div class="caffeine_info_col1">
						<div class="caffeine_title">카페인 계산기</div>
							<div class="calc-area">
							
								<!--계산기 include 부분 -->
								<jsp:include page="/views/components/calculator.jsp"></jsp:include>
							</div>
					</div>
					<div class="caffeine_info_col2">
						<img src="/resources/imgs/caffeine_info_mark4.png" alt="caffeine_info_image" class="caffeine_info_image">
					</div>
				</div>
			</div>	
			
			<!-- 커스텀 레시피 추천 -->
			<div class="main_col_2_row2">
				<div id="imgboard" class="slideshow-container">
				    <c:forEach var="item" items="${main.customBoardListDao}">
				        <div class="mySlides fade">
				            <div class="boardCard">
					            <a href="CustomBoardViewHandler.do?CUS_NUM=${item.cus_num}">
					                    <img src="upload/${item.cus_img_realname}" alt="Image" id="img" class="img_basic">
					                <div class="cardTextBox">
			                           <p><b>${item.m_id}</b>     ${item.cus_regdate}<br/>
			                           <b>${item.cus_title}</b><br/><!--${item.cus_content}--></p>
			                        </div>
					            </a>
			                </div>
				       </div>
				    </c:forEach>
				</div>
			</div>	
			
		</div>
	</div>	
</body>

<script>
//슬라이드 스크립트
	var slideIndex = 0;
	showSlides();
	
	function showSlides() {
	    var slides = document.getElementsByClassName("mySlides");
	    for (var i = 0; i < slides.length; i++) {
	        slides[i].style.display = "none";
	    }
	    slideIndex++;
	    if (slideIndex > slides.length) {
	        slideIndex = 1;
	    }
	    slides[slideIndex - 1].style.display = "block";
	    setTimeout(showSlides, 3000); // 3초마다 전환
	}
</script>
<script>
//계산기 스크립트
	let calcResult = "${main.calculationResult}";
	const isAuth = "${isAuth}"==="true"? true : false;
	const hasCoffees = false;
    const hasCalc = true;
</script>

<script>
//프로필수정.do
const editProfileBtn = document.querySelector(".main_profile__btn");
editProfileBtn.addEventListener("click", function(){
    window.location.href = '/editMyProfilePageMove.do';
})
</script>

<script src="https://unpkg.com/react@17.0.2/umd/react.production.min.js"></script>
<script src="https://unpkg.com/react-dom@17.0.2/umd/react-dom.production.min.js"></script> 
<script src="https://unpkg.com/@babel/standalone/babel.min.js"></script> 

<script src="https://kit.fontawesome.com/9e2cfcdf3a.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/progressbar.js@1.1.1/dist/progressbar.min.js" integrity="sha256-CjGwkk3nsu5BkdGgSjediSja+n8zB6HARhF/eZxtO0g=" crossorigin="anonymous"></script>

<script src="/resources/js/favoritelist.js" type="text/babel"></script>
<script src="/resources/js/calculator.js"></script>
<script src="/resources/js/main.js" type="text/babel"></script>

</html>