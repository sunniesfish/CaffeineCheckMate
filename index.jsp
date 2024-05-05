<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
</script>
</head>
<body>
      <h1>  Test</h1>
        <div>
        <a href="/test.do">test</a>
        <% String testresult = (String) request.getAttribute("test"); %>
        <h1><%=testresult %></h1>
        back_pastelq
        </div>
        
        <div>
        <h1>커피리스트</h1>
        <form action="/coffeeList.do" method="get">
        <input type="submit">
        </form>

        <a href="mypage.do">My Page</a>
        </div>
  </body>
  </html>