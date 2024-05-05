<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="/resources/css/coffeedetail.css">
<meta charset="UTF-8">
<title>COFFEELIST UPDATE(master)</title>
<script src="../../resources/js/coffeeList.js"></script>

</head>
<body>
<%request.setCharacterEncoding("utf-8");%>
<div class="coffeeupaddsection">
<h2 class="coffeeupaddH2">COFFEE UPDATE</h2>
	<form class="coffeewrapform" action="coffeeListUpdate.do" method="post" enctype="multipart/form-data" onsubmit="return coffee_FileExtensionsError()">
	    <div class="form-detail">
	    	<div class="detail">
			    <input type="hidden" name="coffeeNo" value="${coffeeUpdateForm.c_NO}"/>
			    <label class="coffeelabel">제품 이름</label>
			    <input type="text" name="cname" value="${coffeeUpdateForm.c_NAME}" required>
			    
			    <label class="coffeelabel">브랜드</label>
			    <input type="text" name="cbrand" value="${coffeeUpdateForm.c_BRAND}" required>
			    
			    <label class="coffeelabel">카페인 함량</label>
			    <input type="number" name="ccaffeine" value="${coffeeUpdateForm.c_CAFFEINE}" required>
			    
			    <label class="coffeelabel">당류 함량</label>
			    <input type="number" name="csaccharide" value="${coffeeUpdateForm.c_SACCHARIDE}" required>
			    
			    <label class="coffeelabel">칼로리</label>
			    <input type="number" name="ccalorie" value="${coffeeUpdateForm.c_CALORIE}" required>
			    
			    <label class="coffeelabel">상세 내용</label>
			    <textarea class="coffeeupaddTextA" name="ccontent" required>${coffeeUpdateForm.c_CONTENT}</textarea>
			    
			    <label class="coffeelabel">음료 타입</label>
			    <select class="coffeeupaddSel" name="ctype" required>
			        <option value="COF" ${"COF".equals(coffeeUpdateForm.c_TYPE) ? "selected" : ""}>Coffee</option>
			        <option value="BEV" ${"BEV".equals(coffeeUpdateForm.c_TYPE) ? "selected" : ""}>Beverage</option>
			        <option value="TEA" ${"TEA".equals(coffeeUpdateForm.c_TYPE) ? "selected" : ""}>Tea</option>
			    </select>
			    
			    <label class="coffeelabel">카페인 정도</label>
			    <select class="coffeeupaddSel" name="cstage" required>
			        <option value="1" ${"1".equals(coffeeUpdateForm.c_STAGE) ? "selected" : ""}>낮음</option>
			        <option value="2" ${"2".equals(coffeeUpdateForm.c_STAGE) ? "selected" : ""}>중간</option>
			        <option value="3" ${"3".equals(coffeeUpdateForm.c_STAGE) ? "selected" : ""}>높음</option>
			    </select>
			</div>
		</div>	   
		<!-- 이미지 업로드 및 미리보기 섹션 -->
    	<div class="form-image">
    		<div class="image">
				<label class="coffeelabel">현재 이미지<br/></label>
			    <img id="coffeePreviewImage" src="${coffeeUpdateForm.c_IMG_COPY}" alt="기존 이미지" style="width:300px;"><br/>
			    
			    변경할 이미지(기존 이미지 유지 희망 시 선택X)<br/>
			    <input type="file" id="cimgreal" name="cimgreal" onchange="coffee_PreviewImage()"><br/>
			    <!-- 수정하지 않으면 이전 값 히든으로 넘김 -->
			    <input type="hidden" name="defaultImage" value="${coffeeUpdateForm.c_IMG_REAL}">
			    
			    <input class="mainbuttonOk" type="button" onclick="coffee_openModal('cupdate')" value="수정">
				<a href="javascript:history.back();"><button class="mainbuttonSub" type="button">이전 페이지</button></a>
				<a href="/coffeelist.do"><button class="mainbuttonSub" type="button">커피리스트 메인 페이지</button></a>
			</div>
		</div>	  	
		  
    <!-- 모달 창 시작-->
	<div id="cupdate" class="coffeelist-modal">
		<span onclick="coffee_closeModal('cupdate')" class="coffeelist-modalclosebtn" title="Close Modal">x</span>
		<div class="coffeelist-modalcontent">
			<div class="coffeelist-modalcontainer">
				<h1>Update</h1>
				<p>정말로 수정하시겠습니까?</p>
				<div class="coffeelist-modalclearfix">
					<input class="coffeelist-modalcancelbtn" type="button" onclick="coffee_closeModal('cupdate')" value="돌아가기"/>
					<input class="coffeelist-modalOkbtn" type="submit" value="수정하기"/>
				</div>
			</div>
		</div>
	</div>
<!-- 모달 창 끝--> 
</form>
</div>
</body>
</html>