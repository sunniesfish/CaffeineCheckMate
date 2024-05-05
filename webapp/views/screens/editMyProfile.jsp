<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <title>프로필 수정</title>
    <!-- 부트스트랩 CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style type="text/css">
    	<!-- CSS로 파일 입력 필드 감추기 -->
        .custom-file-input {
            position: absolute;
            left: -9999px;
        }
        .custom-file-label {
            display: inline-block;
            cursor: pointer;
        }
        .profile-image {
            display: block;
            margin: auto;
            width: 300px;
            height: 300px;
            object-fit: cover;
        }
        /* 이미지 미리보기 영역 */
        .image-preview {
            display: none;
            width: 300px;
            height: 300px;
            margin-top: 20px;
            object-fit: cover;
        }
    </style>    
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">프로필 수정</h2>
        <form action="/editMyProfileUpdate.do" method="post" enctype="multipart/form-data"> <!-- 프로필 수정 처리 URL -->
	        <c:if test="${not empty profile.userProfileDTO.p_IMG_COPY}">
	       		<img src="/resources/profile/${profile.userProfileDTO.p_IMG_COPY}" id="beforeImage" class="profile-image" alt="프로필 이미지"> <!-- 프로필 사진 표시 -->
	        	<input type="hidden" value="${profile.userProfileDTO.p_IMG_COPY}" name="p_IMG_COPY">
	        </c:if>
            <img src="" alt="이미지 미리보기" id="profile-image" class="profile-image image-preview">
            <div class="form-group">
                <label for="userid">아이디</label>
                <input type="text" class="form-control" id="m_ID" name="m_ID" value="${profile.userProfileDTO.m_ID}" readonly>
            </div>
            <div class="form-group">
                <label for="nickname">닉네임</label>
                <input type="text" class="form-control" id="m_NICKNAME" name="m_NICKNAME" value="${profile.userProfileDTO.m_NICKNAME}">
            </div>
            <div class="form-group">
                <label for="userid">몸무게</label>
                <input type="text" class="form-control" id="p_WEIGHT" name="p_WEIGHT" value="${profile.userProfileDTO.p_WEIGHT}">
            </div>
            <div class="form-group">
                <label for="profileImage">프로필 이미지</label>
                <div class="custom-file">  <!-- 파일 업로드 필드 감추기 -->
                    <input type="file" class="custom-file-input" id="p_IMG_REAL" name="p_IMG_REAL">
                    <label class="custom-file-label" for="p_IMG_REAL">파일 선택</label>  <!-- "파일 선택" 텍스트 -->
                </div>
                <input type="text" class="form-control mt-2" id="fileNameDisplay" value="${profile.userProfileDTO.p_IMG_REAL}" readonly>  <!-- 파일명 표시 -->
            </div>
            <div class="text-center">
                <button type="submit" class="btn btn-primary">수정하기</button>
                <a href="mypage.do" class="btn btn-secondary">취소</a> <!-- 마이페이지로 돌아가기 -->
            </div>
        </form>
    </div>

    <!-- 부트스트랩 JS 및 의존성 -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    
    <!-- jQuery 이벤트로 파일 이름 표시 -->
	<script>
		$(document).ready(function() {
		    // 파일 선택 input 요소의 변경 이벤트를 감지합니다.
		    $('#p_IMG_REAL').on('change', function() {
		        
		    	var file = this.files[0]; // 선택된 파일을 가져옵니다.
		        
		        if (file) {
		            var reader = new FileReader(); // FileReader 객체를 초기화합니다.
		            reader.onload = function(e) {
		            	$('#beforeImage').css('display', 'none');
		                // 이미지 미리보기로 선택된 이미지를 설정합니다.
		                $('.image-preview').attr('src', e.target.result);
		                // 이미지 미리보기를 표시합니다.
		                $('.image-preview').css('display', 'block');
		            }
		            reader.readAsDataURL(file); // 선택된 파일을 Data URL로 읽어옵니다.
		        
		        } else {
		            // 파일이 선택되지 않은 경우 이미지 미리보기를 지웁니다.
		            $('.image-preview').attr('src', '');
		            // 이미지 미리보기를 숨깁니다.
		            $('.image-preview').css('display', 'none');
		        }

		        var fileName = $(this).val().split('\\').pop(); // 파일 이름을 추출합니다.
		        // 텍스트 입력란에 파일 이름을 표시합니다.
		        $('#fileNameDisplay').val(fileName);
		        // 사용자 정의 파일 라벨에 파일 이름을 표시합니다.
		        $('.custom-file-label').text(fileName);
		    });
        });
    </script>
</body>
</html>