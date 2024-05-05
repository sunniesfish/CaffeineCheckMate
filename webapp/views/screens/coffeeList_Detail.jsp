<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/resources/css/coffeedetail.css">
<title>COFFEELIST Detail</title>
</head>
<body>
<section class="detailSection">
	<div class="coffeedetailcontainer">
		<h1 class="detail-h1">${coffeeDetail.c_NAME}</h1>
		<img src="${pageContext.request.contextPath}${coffeeDetail.c_IMG_COPY}" alt="커피 이미지">
		
			<div class="coffeedetailcontent">
				<h3 class="detail-h3">Brand</h3>
				<div>${coffeeDetail.c_BRAND}</div>
				<h3 class="detail-h3">Description</h3>
				<div>${coffeeDetail.c_CONTENT}</div>
			</div>
		
			<table class="coffeedetailtable">
				<tr>
					<th>카페인</th>
					<th>당도</th>
					<th>칼로리</th>
				</tr>
			<tbody>
				<tr>
					<td>${coffeeDetail.c_CAFFEINE}</td>
					<td>${coffeeDetail.c_SACCHARIDE}</td>
					<td>${coffeeDetail.c_CALORIE}</td>
				</tr>
			</tbody>
			</table>
		
		<div>
			<button class="coffeedetailbtn" onclick="window.close()">창 닫기</button>
		</div>
	</div>
</section>
</body>
</html>