<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>탈퇴완료페이지</title>
<link href="/resources/css/okDelete.css"rel="stylesheet" type="text/css">
</head>
<body>
<%
String dday = (String)session.getAttribute("D_day");
System.out.println(dday);
session.invalidate();
%>
<input type="hidden" value="<%= dday%>" name="dday" id="dday">
<div class="container">
	<div>
		<h1>탈퇴진행중입니다...</h1>
		<br/>
		<h3 style=color:red;> 탙퇴는 신청을하고 7일 후 완료됩니다.</h3>
	</div>
<% if(dday!=null){%>
  	<h1>탈퇴까지 남은 시간</h1>
  	<div id="clock" class="clock">
	  	<h2 id="deleteTime"></h2>
	  	<br/>
  	</div>
<%}%>
	<div>
		<form action="/views/screens/identify.jsp" name="deleteCancelForm" id="deleteCancelForm" method="post">
		    <input type="hidden" value="deleteCancel" name="Value"/>
		    <input type="submit" value="탈퇴취소"/>
		    <input type="button" value="홈으로" onclick="location.href='/views/screens/Main.jsp'">
		</form>
	</div>
</div>
	<script type="text/javascript" src="/resources/js/memberOkDelete.js"></script>
</body>
</html>