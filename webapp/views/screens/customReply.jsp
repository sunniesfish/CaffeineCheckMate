<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reply</title>
<link rel="stylesheet" href="/resources/css/customReply.css">
<%
    // 세션에서 AUTH_USER_ID 값을 가져와서 JavaScript 변수에 할당
    HttpSession sessionId = request.getSession(false);
    String m_id = (String) sessionId.getAttribute("AUTH_USER_ID");

    String cus_NUM = request.getParameter("cus_no");
	int cus_no = 0;
	cus_no = Integer.parseInt(cus_NUM);
%>

<!-- 게시판 번호 히든 value에 저장 -->
<input type="hidden" id="cus_no_hidden" value="<%= cus_no %>">
<input type="hidden" id="m_id_hidden" value="<%= m_id %>">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/customReply.js"></script>

</head>
<body>
	<!--댓글 입력-->
	<div class="reply-input-container" align="center">
	    <table class="input_table">
	        <tr>
	            <td rowspan="2" style="vertical-align: middle; text-align: center;">
	                <!--프로필 사진-->
	              <%--   <div class="box" style="background: white;">
	                    <img class="profile" src="<%=request.getContextPath()%>/resources/imgs/profile.png" style="width: 40px; height: 40px; vertical-align: middle;">
	                </div> --%>
	            </td>
	        </tr>
	        <tr>
	            <td style="width: 80%;">
	                <div class="relpyForm">
	                    <label for="reply"></label> 
	                    <input type="text" class="form-control" id="reply"  name="reply" placeholder="   댓글을 입력하세요.">
	                </div>
	            </td>
	            <td><button class="replybtn" onclick="insertReply();">게시하기</button></td>
	        </tr>
	    </table>
	</div>

	<br/>
	
	<!-- 댓글 수정 -->
	<div id="editModal" class="modal" align="center" style="display: none;">
	    <div class="update-modal-content">
	        <!-- 닫힘 버튼 -->
	        <input id="editedReply" class="form-control">
	        <button id="saveBtn" class="replybtn" onclick="saveEditedReply();">수정저장</button>
	    </div>
	</div>
		
	
	<!--댓글 조회-->
	<div id="replyList" align="center">
		<!--각 댓글 영역 -->
		<div class=align="center">
			<table style="width: 500px;">
				<tbody class="replyArea" id="replyArea">

				</tbody>
			</table>
		</div>
	</div>
	

</body>
</html>