<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/custom.css">
<title>글작성</title>
<script type="text/javascript" src="/resources/js/custom.js"></script>
</head>
<body>
<form action="/CustomBoardAddHandler.do" id="add" name="add" method="post" enctype="multipart/form-data" class="add_form">

	<div class="add_left_content">
		<label for="file">
	    	<img id="preview" src="/resources/imgs/recipeDefault.png" class="add_image_preview"><br>
  			<!-- 	<div class="add_image_button">파일 업로드하기</div> -->
		</label>
	    <input type="file" name="file" id="file" onchange="previewImage()"><br/>
    </div>
    
    <div class="add_right_content">
	    <p><input type="text" size="30" id="cus_name" name="cus_name"  placeholder="  제목을 입력하세요" class="add_title"></p>
	    <p><textarea cols="50" rows="10" id="cus_content" name="cus_content" placeholder=" 나만의 레시피를 작성해 보세요" class="add_content"></textarea></p>
	    <input type="hidden" id="num" name="num" value="${item.cus_num}" />
	<div class="selection-group">
	    <select name="shot" id="shot" class="add_option">
	        <option value="0">샷</option>
	        <option value="0">없음</option>
	        <option value="1">1번</option>
	        <option value="2">2번</option>
	        <option value="3">3번</option>
	        <option value="4">4번이상</option>
	    </select>
	    <select name="milkType" id="milkType" class="add_option">
	        <option value="noMilk">우유</option>
	        <option value="저지방우유">저지방 우유</option>
	        <option value="아몬드브리즈">아몬드 브리즈</option>
	        <option value="오토믹스">오트 믹스</option>
	        <option value="두유">두유</option>
	        <option value="코코넛밀크">코코넛 우유</option>
	    </select>
	    <select name="syrupType" id="syrupType" class="add_option">
	        <option value="noSyrup">시럽</option>
	        <option value="바닐라시럽">바닐라 시럽</option>
	        <option value="카라멜시럽">카라멜 시럽</option>
	        <option value="헤이즐넛시럽">헤이즐넛 시럽</option>
	        <option value="모카시럽">모카 시럽</option>
	        <option value="시나몬시럽">시나몬 시럽</option>
	        <option value="메이플시럽">메이플 시럽</option>
	    </select>
	    <select name="toppingType" id="toppingType" class="add_option">
	        <option value="noTopping">토핑</option>
	        <option value="휘핑크림">휘핑 크림</option>
	        <option value="초콜릿토핑">초콜릿 토핑</option>
	        <option value="카라멜드리즐">카라멜 드리즐</option>
	        <option value="마시멜로">마시멜로</option>
	        <option value="코코아파우더">코코아 파우더</option>
	        <option value="쿠키크럼블">쿠키 크럼블</option>
	        <option value="펄">펄</option>
	    </select>
	    <select name="decaffeinated" id="decaffeinated" class="add_option">
	        <option value="일반">일반</option>
	        <option value="디카페인">디카페인</option>
	    </select>
	</div>
	    <p><input type="submit"  class="add_submit" value="작성하기"></p>
</div>
</form>
</body>
</html>