<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link href="/resources/css/joinRequest.css"rel="stylesheet" type="text/css">

</head>
<body>
	<div class="container">
		<form class="join-form" action="/JoinMemberHandler.do" name="joinForm" method="post" onsubmit="return validateForm()">
	    	<h1 class="header">회원가입</h1>
	    <div class="id">
	      <label for="joinId"></label>
	      <input type="text" name="joinId" id="joinId" placeholder="아이디" size="10">
	      <input type="button" value="중복체크" onclick="return checkId()">
	    </div>
	    <div class="pw">
	      <label for="joinPw1"></label>
	      <input type="password" name="joinPw1" id="joinPw1" placeholder="비밀번호" required="required"><br/><br/>
	      <label for="joinPw2"></label>
	      <input type="password" name="joinPw2" id="joinPw2" placeholder="비밀번호확인" required="required">
	    </div>
	    <div class="name">
	      <label for="joinName"></label>
	      <input type="text" name="joinName" id="joinName" placeholder="이름" required="required">
	    </div>
	    <div class="birth">
	      <label for="birth-year"></label>
	      <select name="birth-year" id="birth-year">
	        <option disabled selected>출생 연도</option>
	      </select>
	      <select name="birth-month" id="birth-month">
	        <option disabled selected>월</option>
	      </select>
	      <select name="birth-day" id="birth-day">
	        <option disabled selected>일</option>
	      </select>
	    </div>
	    <div class="mail">
	      <span style="color:red;">메일인증필수</span>
	      <input type="button" value="메일인증" onclick="joinEmail()">
	    </div>
	    <div class="nick">
	      <label for="joinNick"></label>
	      <input type="text" name="joinNick" id="joinNick" placeholder="닉네임" size="10" maxlength="10" minlength="2" required="required">
	      <input type="button" value="중복체크" onclick="return checkNick()">
	    </div>
	    <div class="tel">
	      <label for="joinTel1"></label>
	      <input type="text" class="tel" name="joinTel1" id="joinTel1" placeholder="010" maxlength="3" minlength="3" size="3" required="required">-
	      <input type="text" name="joinTel2" id="joinTel2" placeholder="0000" maxlength="4" minlength="4" size="4" required="required">-
	      <input type="text" name="joinTel3" id="joinTel3" placeholder="0000" maxlength="4" minlength="4" size="4" required="required">
	    </div>
	    <div class="gender">
	      <label>성별 : </label>
	      <input type="radio" name="joinMW" id="joinM" value="M" checked="checked">남성
	      <input type="radio" name="joinMW" id="joinW" value="W" required="required">여성
	    </div>
	    <div class="sns">
	      <label>SNS 수신여부 : </label>
	      <input type="radio" name="joinSnsYN" id="joinSnsY" value="Y" checked="checked">동의&nbsp;&nbsp;&nbsp;
	      <input type="radio" name="joinSnsYN" id="joinSnsN" value="N" required="required">거절&nbsp;&nbsp;&nbsp;
	    </div>
	    <div class="btn">
	      <input type="submit" value="가입">
	      <input type="button" onclick="location.href='/views/screens/login.jsp'" value="취소">
	    </div>
	  </form>
	</div>
<script type="text/javascript" src="/resources/js/memberJoinRequest.js"></script>
</body>
</html>