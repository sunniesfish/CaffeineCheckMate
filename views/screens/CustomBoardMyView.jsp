<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/resources/css/custom.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/custom.js"></script>   
<meta charset="UTF-8">
<title>CCM</title>

</head>
<body>

<span class="wrapper" style="display: inline-block;">
		<!-- 헤더 -->
	<jsp:include page="/views/components/header.jsp" />
		
	<div class="view_form">
	    <form action="/views/screens/CustomBoardUpdate.jsp" method="post" id="updateform">
			<c:forEach var="item" items="${list}">
						<input type="hidden" name="img" id="img" value="${item.cus_img_realname}" />
						<input type="hidden" id="num" name="num" value="${item.cus_num}" />    
						<input type="hidden" name="content" id="content" value="${item.cus_content}" />
						<input type="hidden" name="title" id="title" value="${item.cus_title}" />
						
						<div class="view_left_content">
							<img src="upload/${item.cus_img_realname}" alt="Image" class="image_view">
						</div>
			</c:forEach>  
		</form>
						
		<form action="/views/screens/CustomBoardUpdate.jsp" method="post" id="updateform">
			<c:forEach var="item" items="${list}">
						<input type="hidden" name="img" id="img" value="${item.cus_img_realname}" />
						<input type="hidden" id="num" name="num" value="${item.cus_num}" />    
						<input type="hidden" name="content" id="content" value="${item.cus_content}" />
						<input type="hidden" name="title" id="title" value="${item.cus_title}" />
						
						<div class="view_right_content">
							<div class="view_id"><b>${item.m_id}</b></div>
							<div class="view_regdate">${item.cus_regdate}</div>
							<hr/><br/>
							<div class="view_title"><b> ${item.cus_title}</b></div>
							<br/>
							<div class="view_content_box">
								<div class="view_content">${item.cus_content}</div>
							</div>
							<br/><hr/>
							<div class="option_tag">
								<div>#${item.shot}샷</div>
								<div>#${item.milkType}</div>
								<div>#${item.syrupType}</div>
								<div>#${item.toppingType}</div>
								<div>#${item.decaffeinated}</div>
							</div>
							<div><br/>
						        <input type="hidden" name="no" value="${param.CUS_NUM}" />
						        <input class="update_button" type="submit" value="수정">
								<button class="delete_button" type="button"id="delbutton" onclick="location.href='/CustomBoardDelete.do?num=${param.CUS_NUM}'">삭제</button>
							</div>
						</div>
			</c:forEach>  
	    </form>
	</div>
</span>

</body>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/progressbar.js@1.1.1/local-dev/main.min.css"> 
</html>