<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/resources/css/checkNICK.css"rel="stylesheet" type="text/css">
</head>
<body>
<div class="container">

</br></br></br>

<h3>닉네임 중복확인</h3>
	<form action="/CheckHandler.do" method="get" name="joinForm">
		아이디 : <input type="text" name="joinNick" value='${joinPut}'>
			<input type="submit" value="중복 체크">
	</form>
<c:if test="${result == 1}">
		<script>
			//opener란 이창을 열어준 창을 말한다. 즉 여기서는 회원가입폼
			opener.document.joinForm.joinNick.value = "";	//입력한 값을 없애줌
		</script>
			${joinPut}는 이미 사용중인 아이디입니다.
</c:if>
	
	<c:if test="${result == 0}">
		</br>${joinPut}는 사용가능한 아이디입니다.</br></br>
			<input type="button" value="사용" onclick="return idok('${joinPut}')">
	</c:if>
<script>
//	function idok(joinPut){
//		opener.setNickValue(joinPut);
//		//전달하면서 창을 닫겠다.
//		self.close();
//		}
	function idok(joinPut){
		//opener.setIdValue(joinPut);
		opener.setNickValue(joinPut, true);
		//전달하면서 창을 닫겠다.
		self.close();
		}
</script>
</div>

</body>
</html>