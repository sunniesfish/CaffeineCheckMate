<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/resources/css/coffeelist.css">
    <%
	boolean isAuth = request.getSession().getAttribute("AUTH_USER_ID") != null ? true:false;
	pageContext.setAttribute("isAuth", isAuth);
	%>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>COFFEE LIST INDEX</title>
<script src="../../resources/js/coffeeList.js"></script>
</head>
<body>
    <div class="wrapper">
            <!-- 헤더 -->
            <jsp:include page="/views/components/header.jsp" />
            <div id="coffeelist">
                <div class="coffeelist-column_1">
                    <div class="coffeelist-column__header">
                        <div class="coffeelist-title clickable">Coffees</div><br/>
                        <div class="coffeelist-searchbox">
                            <c:if test="${isAdmin}">
                                <div class="coffeelist-add">
                                    <a href="./views/screens/coffeeList_Add.jsp">Add Coffee</a>
                                </div>
                            </c:if>
                            <form action="coffeeList.do" method="get">
                                <select class="coffeelist-searchbox__select" name="searchType">
                                    <option value="name">제품명</option>
                                    <option value="brand">브랜드</option>
                                </select>	
                                <input class="coffeelist-searchbox__input" type="text" name="searchQuery" required="required">
                                <button class="coffeelist-searchbox__btn clickable"><i class="fa-solid fa-magnifying-glass"></i></button>
                            </form>
                        </div>
                    </div>
                    <div class="coffee-box">
                        <c:forEach var="coffeeV" items="${CoffeeListPage.coffeeList}" varStatus="status">
                            <!-- 모달 창 시작-->
                            <div id="${coffeeV.c_NO}" class="coffeelist-modal">
                                <span onclick="coffee_closeModal('${coffeeV.c_NO}')" class="coffeelist-modalclosebtn" title="Close Modal">x</span>
                                <form class="coffeelist-modalcontent" method="post" action="coffeeListDelete.do?coffeeNo=${coffeeV.c_NO}">
                                    <div class="coffeelist-modalcontainer">
                                        <h1>Delete Account</h1>
                                        <p>삭제된 게시물은 되돌릴 수 없습니다. 정말로 삭제하시겠습니까?</p>
                                        <div class="coffeelist-modalclearfix">
                                            <input class="coffeelist-modalcancelbtn" type="button" onclick="coffee_closeModal('${coffeeV.c_NO}')" value="돌아가기"/>
                                            <input class="coffeelist-modalOkbtn" type="submit" value="삭제하기"/>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <!-- 모달 창 끝-->     
                            <div class="coffeelist-container">
                                <div class="coffeelist-item" value="C_NO=${coffeeV.c_NO}">
                                    <img class="coffeelist-item__img" src="${pageContext.request.contextPath}${coffeeV.c_IMG_COPY}" alt="${coffeeV.c_NAME} Image"/>
                                    <div class="coffeelist-item__name clickable">
                                        <a href="coffeeListDetail.do?coffeeNo=${coffeeV.c_NO}" onclick="window.open(this.href, '_blank', 'width=450, height=550 scrollbars=no'); return false;">${coffeeV.c_NAME}</a>
                                    </div>
                                    <!-- 회원만 조회할 수 있는 즐겨찾기 버튼 -->
                                    <div class="coffeelist-item__info">${coffeeV.c_BRAND}</div>
                                    <div class="coffeelist-item__btn-area">
                                        <c:if test="${isAdmin}">
                                            <a class="coffeelist-item__modify-btn clickable" href="coffeeListUpdateForm.do?coffeeNo=${coffeeV.c_NO}">
                                                <button><i class="fa-solid fa-wrench clickable"></i></button>
                                            </a>
                                            <button class="coffeelist-item__delete-btn clickable" onclick="coffee_openModal('${coffeeV.c_NO}')"><i class="fa-solid fa-trash"></i></button>
                                        </c:if>
                                        <c:if test="${not empty sessionScope.AUTH_USER_ID}">
                                            <button style="display: inline-block;" class="addFav-btn coffeelist-item__fav clickable">
                                                <i class="fa-solid fa-plus"></i>
                                            </button>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                        <c:if test="${CoffeeListPage.hasNoCoffeeList()}">
                            <div class="coffeelist-item">찾는 게시물이 없습니다</div>
                        </c:if>
                    </div>
                    
                    <!-- 페이지네이션 아래 블럭을 담당하는 부분 -->
                    
                    <div class="pagination">
                    <c:choose>
                        <c:when test="${CoffeeListPage.hasCoffeeList()}">
                            <!-- 게시물이 있을 경우 페이징 넘버 처리 -->
                            <c:if test="${CoffeeListPage.startPage > 1}">
                                <a class="pagination-a" href="?page=${CoffeeListPage.startPage - 1}">이전</a>
                            </c:if>
                                <c:forEach begin="${CoffeeListPage.startPage}" 
                                        end  ="${CoffeeListPage.endPage}"  var="i">
                                    <c:choose>
                                        <c:when test="${i == CoffeeListPage.currentPage}">
                                            <span><b>${i}</b></span>
                                        </c:when>
                                        <c:otherwise>
                                            <a class="pagination-a clickable" href="?page=${i}">${i}</a>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            <c:if test="${CoffeeListPage.endPage < CoffeeListPage.totalPage}">
                                    <a class="pagination-a clickable" href="?page=${CoffeeListPage.endPage + 1}">다음</a>
                            </c:if>	
                        </c:when>
                        <c:otherwise>
                            <a href="/coffeeList.do"><button type="button">기본 리스트로 돌아가기</button></a>
                        </c:otherwise>
                    </c:choose>  
                    </div>
                </div>
            
                <c:if test="${not empty sessionScope.AUTH_USER_ID}">
                    <div class="coffeelist-column_2 hidden">
                        <div class="coffeelist-column_2__title"><h3>Favorites</h2></div>
                            <button class="coffeelist-column_2__togle-fav-del clickable">Remove</button>
                        <div class="favbox">
                            <!--즐겨찾기 include 부분-->
                            <jsp:include page="/views/components/favorites.jsp"></jsp:include>
                        </div>
                    </div>       
                </c:if>
                <button class="coffeelist-column_3 clickable not-rotated hidden"><i class="fa-solid fa-chevron-left"></i></button>
            </div>
    </div>
</body>
<script>
	const isAuth = "${isAuth}"==="true"? true : false;
	const hasCoffees = true;
    const hasCalc = false;
    const contextPath = '<%= request.getContextPath() %>';
</script>
<script src="https://kit.fontawesome.com/9e2cfcdf3a.js" crossorigin="anonymous"></script>
<script src="https://unpkg.com/react@17.0.2/umd/react.production.min.js"></script>
<script src="https://unpkg.com/react-dom@17.0.2/umd/react-dom.production.min.js"></script> 
<script src="https://unpkg.com/@babel/standalone/babel.min.js"></script> 
<script src="/resources/js/favoritelist.js" type="text/babel"></script>
<script src="/resources/js/coffeelist-index.js"></script>
</html>