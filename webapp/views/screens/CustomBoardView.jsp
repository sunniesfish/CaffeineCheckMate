<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/resources/css/custom.css">
<meta charset="UTF-8">
<title>CCM</title>
<%
// 세션에서 AUTH_USER_ID 값을 가져와서 JavaScript 변수에 할당
HttpSession sessionId = request.getSession(false);
String m_id = (String) sessionId.getAttribute("AUTH_USER_ID");
%>
<input type="hidden" id="m_id_hidden" value="<%=m_id%>">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>

<body>

<span class="wrapper" style="display: inline-block;">
		<!-- 헤더 -->
		<jsp:include page="/views/components/header.jsp" />

		<div class="view_form">
			<c:forEach var="item" items="${list}">
				<div class="view_left_content">
					<img src="upload/${item.cus_img_realname}" alt="Image" class="image_view">
				</div>	
				
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
					
					<br/>
					<div>
						<!-- 댓글 버튼 -->
						<button class="reply_button" onclick="showReplyModal('${item.cus_no}')">댓글</button>
					</div>
				</div>
			</c:forEach>
		</div>

		<!-- 모달을 나타내는 HTML -->
		<div id="modal" class="modal-container">
			<button onclick="closeModal()">닫기</button>
			<div class="modal-content" id="modal-content"></div>
		</div>
</span>		

</body>

<script>
	    // 댓글 모달을 보여주는 함수
	    function showReplyModal(cus_no) {
	        var modal = document.getElementById("modal");
	        modal.style.display = "block";
	
	        // AJAX를 통해 다른 JSP 페이지를 불러와 모달에 표시
	        var xhr = new XMLHttpRequest();
	        
	        var encodedCusNo = encodeURIComponent(cus_no);
	        var m_id = document.getElementById("m_id_hidden").value;
	        
	        xhr.open("POST", "<%=request.getContextPath()%>/views/screens/customReply.jsp?cus_no=" + encodedCusNo);
	        xhr.send();
	        
	        xhr.onreadystatechange = function() {
	            if (xhr.readyState == 4 && xhr.status == 200) {
	                var modalContent = document.getElementById("modal-content");
	                modalContent.innerHTML = xhr.responseText;
	
	                // 외부 JavaScript 파일의 경로
	                var jsFilePath = "<%=request.getContextPath()%>/resources/js/customReply.js";

				// 외부 JavaScript 파일을 가져와서 모달에 추가
				var scriptElement = document.createElement("script");
				scriptElement.src = jsFilePath;
				modalContent.appendChild(scriptElement);
			}
		};
	}

	// 모달을 닫는 함수
	function closeModal() {
		var modal = document.getElementById("modal");
		modal.style.display = "none";
	}
	
   $(document).ready(function() {
       $(document).keydown(function(event) {
           if (event.which === 27) {
              closeModal();
           }
       });
   });
</script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/progressbar.js@1.1.1/local-dev/main.min.css"> 
</html>