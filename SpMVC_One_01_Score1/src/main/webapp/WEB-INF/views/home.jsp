<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var = "rootPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대한 고교 성적처리</title>
<link href="${rootPath}/static/css/layout.css?ver=2021-06-18-003"
		rel="stylesheet"/>
</head>
<body>
	<%@ include file = "/WEB-INF/views/include/include_nav.jspf" %>
	<table class="list">
      <tr>
        <th>학번</th>
        <th>이름</th>
        <th>전공</th>
        <th>학년</th>
        <th>응시과목</th>
        <th>총점</th>
        <th>평균</th>
      </tr>
      <c:forEach items="${HMLIST}" var="HM">
	      <tr>
	        <td>${HM.h_num}</td>
	        <td>${HM.h_name}</td>
	        <td>${HM.h_dept}</td>
	        <td>${HM.h_grade}</td>
	        <td>${HM.h_count}</td>
	        <td>${HM.h_sum}</td>
	        <td>${HM.h_avg}</td>
	      </tr>
	  </c:forEach>
	</table>
</body>
</html>