<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
  <%request.setCharacterEncoding("utf-8");%>
 
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/resources/css/custom.css">
<meta charset="UTF-8">
<title>Update</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<c:set var="img" value="${param.img}"/>
<script type="text/javascript"> var img = ${img}; </script>
<script type="text/javascript" src="/resources/js/custom.js"></script>
</head>

<body>

<span class="wrapper" style="display: inline-block;">
	<!-- 헤더 -->
	<jsp:include page="/views/components/header.jsp" />

	<c:set var="content" value="${param.content}" />
	<c:set var="title" value="${param.title}" />
	
		<form action="/CustomBoardUpdateHandler.do" id="add" name="add" method="post" enctype="multipart/form-data" class="update-form">
			 <input type="hidden" id="num" name="num" value="${param.no}"/> 
			 <input type="hidden" id="img" name="img" value="${img}"/> 
	    
	    	<div class="grid-item" id="div1" name="div1" class="update_left_content">
		  		<label for="file">
					<img id="preview" src="/upload/${img}" alt="미리보기" class="add_image_preview"><br>
		    	</label> 
			    	<input type="file" class="file" id="file" name="file" onchange="previewImage()"><br>
		    </div>
		    
		     <div class="grid-item" id="div1" name="div1" class="update_left_content">
			    <p><input type="text" size="30" id="cus_name" name="cus_name" value="${title}"  class="add_title"></p>
			    <p><textarea cols="50" rows="10" id="cus_content" name="cus_content" class="add_content">${content}</textarea></p>
		
				<div class="selection-group">
				    <select name="shot" id="shot"  class="add_option">
				        <option value="0">샷</option>
				        <option value="0">없음</option>
				        <option value="1">1번</option>
				        <option value="2">2번</option>
				        <option value="3">3번</option>
				        <option value="4">4번이상</option>
				    </select>
				    <select name="milkType" id="milkType"  class="add_option">
				        <option value="noMilk">우유</option>
				        <option value="저지방우유">저지방 우유</option>
				        <option value="아몬드브리즈">아몬드 브리즈</option>
				        <option value="오토믹스">오트 믹스</option>
				        <option value="두유">두유</option>
				        <option value="코코넛밀크">코코넛 우유</option>
				    </select>
				    <select name="syrupType" id="syrupType"  class="add_option">
				        <option value="noSyrup">시럽</option>
				        <option value="바닐라시럽">바닐라 시럽</option>
				        <option value="카라멜시럽">카라멜 시럽</option>
				        <option value="헤이즐넛시럽">헤이즐넛 시럽</option>
				        <option value="모카시럽">모카 시럽</option>
				        <option value="시나몬시럽">시나몬 시럽</option>
				        <option value="메이플시럽">메이플 시럽</option>
				    </select>
				    <select name="toppingType" id="toppingType"  class="add_option">
				        <option value="noTopping">토핑</option>
				        <option value="휘핑크림">휘핑 크림</option>
				        <option value="초콜릿토핑">초콜릿 토핑</option>
				        <option value="카라멜드리즐">카라멜 드리즐</option>
				        <option value="마시멜로">마시멜로</option>
				        <option value="코코아파우더">코코아 파우더</option>
				        <option value="쿠키크럼블">쿠키 크럼블</option>
				        <option value="펄">펄</option>
				    </select>
				    <select name="decaffeinated" id="decaffeinated"  class="add_option">
				        <option value="일반">일반</option>
				        <option value="디카페인">디카페인</option>
				    </select>
				</div>
				<div>
					<p><input class="add_submit" type="submit" value="수정하기"></p>
				</div>
			</div>
		</form>
</span>
</body>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/progressbar.js@1.1.1/local-dev/main.min.css"> 
</html>