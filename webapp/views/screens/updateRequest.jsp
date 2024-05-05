<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/resources/css/updateRequest.css" rel="stylesheet" type="text/css">
<!-- 임시 모달 코드 -->
<style>
/* 모달을 위한 스타일 */
.modal-container {
	display: none; /* 기본적으로 모달은 숨겨져 있어야 함 */
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.5); /* 배경을 어둡게 함 */
	z-index: 9999; /* 다른 요소 위에 모달이 나타나도록 함 */
}
.modal-content {
	background-color: #fff;
	width: 500px;
	margin: 100px auto; /* 화면 중앙에 위치하도록 함 */
	padding: 20px;
	border-radius: 5px;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.5); /* 그림자 효과 추가 */
}
</style>

<script>
    // 댓글 모달을 보여주는 함수
    function showPasswordModal() {
        var modal = document.getElementById("modal");
        modal.style.display = "block";

        // AJAX를 통해 다른 JSP 페이지를 불러와 모달에 표시
        var xhr = new XMLHttpRequest();
        
        xhr.open("POST", "<%=request.getContextPath()%>/views/screens/passwordChange.jsp");
		xhr.send();

		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				var modalContent = document.getElementById("modal-content");
				modalContent.innerHTML = xhr.responseText;

			}
		};
	}

	// 모달을 닫는 함수
	function closeModal() {
		var modal = document.getElementById("modal");
		modal.style.display = "none";
	}
</script>
</head>
	<body>
		<div class="container">
    		<h1>회원정보수정</h1>
			    <%
			    String UpdateID = (String) session.getAttribute("AUTH_USER_ID");
			    String UpdateNAME = (String) session.getAttribute("AUTH_USER_NAME");
			    String UpdateSSN = (String) session.getAttribute("AUTH_USER_SSN");
			    String UpdateEMAIL = (String) session.getAttribute("AUTH_USER_EMAIL");
			    String UpdateNICKNAME = (String) session.getAttribute("AUTH_USER_NICKNAME");
			    String UpdateTEL = (String) session.getAttribute("AUTH_USER_TEL");
			    String UpdateGENDER = (String) session.getAttribute("AUTH_USER_GENDER");
			    String UpdateSNS = (String) session.getAttribute("AUTH_USER_SNS");
			    %>
		    <form action="/UpdateMemberHandler.do" name="updateForm" id="updateForm" method="post" class="grid-form">
		      <div class="grid-item">
		        <label for="id">아이디:</label>
		        <input type="text" id="id" value="<%=UpdateID%>" style="border: none" readonly />
		      </div>
		      <div class="grid-item">
		        <label for="password">비밀번호:</label>
		        <input type="button" value="변경" onclick="showPasswordModal()" />
		      </div>
		        <!-- 모달을 나타내는 HTML -->
				  <div id="modal" class="modal-container">
				    <button onclick="closeModal()">닫기</button>
				    <!-- 모달 내용 영역(비워두세요) -->
				    <div class="modal-content" id="modal-content"></div>
				  </div>
		      <div class="grid-item">
		        <label for="name">이름:</label>
		        <input type="text" id="name" value="<%=UpdateNAME%>" style="border: none" readonly />
		      </div>
		      <div class="grid-item">
		        <label for="ssn">생년월일:</label>
		        <input type="text" id="ssn" value="<%=UpdateSSN%>" style="border: none" readonly />
		      </div>
		      <div class="grid-item">
		        <label for="email">E-mail:</label>
		        <input type="text" id="email" value="<%=UpdateEMAIL%>" style="border: none" readonly />
		      </div>
		      <div class="grid-item">
		        <label for="nickname">닉네임:</label>
		        <input type="text" id="nickname" value="<%=UpdateNICKNAME%>" style="border: none" readonly />
		      </div>
		      <div class="grid-item">
		        <label for="tel">TEL:</label>
		        <input type="text" id="tel" value="<%=UpdateTEL%>" name="updateTel" style="border: none" size=10 readonly />
		        <input type="button" value="변경" onclick="updateTEL()" />
		      </div>
				  <script>
				    function updateTEL() {
				      var url = "/views/screens/updateTEL.jsp"
				      window.open(url, "_blank_1", "width=500,height=300, toolbar=no,menubar=no,resizble=no,scrollbars=yes")
				    }
				  </script>     
		      <div class="grid-item">
		        <label for="gender">성별:</label>
		        <input type="text" id="gender" value="<%=UpdateGENDER%>" style="border: none" readonly />
		      </div>
		      <div class="grid-item sns-container">
		        <label for="sns">SNS 수신여부:</label>
		        <div class="sns-options">
		          <%
		          if (UpdateSNS.equals("동의")) {
		          %>
		          <form action="<%=request.getContextPath()%>/UpdateMemberHandler.do" method="post" name="UpdateSnsForm">
		            <input type="radio" name="updateSnsYN" id="updateSnsY" value="Y" checked="checked" />동의
		            <input type="radio" name="updateSnsYN" id="updateSnsN" value="N" required="required" />거절
		            <input type="hidden" value="UpdateSsn" name="Value">
		            <input type="submit" value="변경" />
		          </form>
		          <%} else {%>
		          <form action="<%=request.getContextPath()%>/UpdateMemberHandler.do" method="post" name="UpdateSnsForm">
		            <input type="radio" name="updateSnsYN" id="updateSnsY" value="Y" required="required" />동의
		            <input type="radio" name="updateSnsYN" id="updateSnsN" value="N" checked="checked" />거절
		            <input type="hidden" value="UpdateSsn" name="Value">
		            <input type="submit" value="변경" />
		          </form>
		          <%}%>
		        </div>
		      </div>
		      <div class="grid-item button-container">
		        <div class="button-group">
		          <input type="button" value="탈퇴" onclick="location.href='/views/screens/identify.jsp?Value=delete'">
		          <span style="margin: 0 5px;"></span>
		          <input type="button" value="취소" onclick="location.href='/views/screens/Main.jsp'" />
		        </div>
		      </div>
		    </form>
  		</div>
	</body>
</html>