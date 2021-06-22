<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대한 고교 성적처리</title>
<link href="${rootPath}/static/css/layout.css?ver=2021-06-22-002"
	rel="stylesheet" />
</head>
<body>
	<div id="container">
		<%@ include file="/WEB-INF/views/include/include_nav.jspf"%>
		<table class="list">
			<tr>
				<th>학번</th>
				<th>이름</th>
				<th>전공</th>
				<th>학년</th>
				<th>전화번호</th>
				<th>주소</th>
			</tr>
			<c:forEach items="${STLIST}" var="ST">
				<tr>
					<td>${ST.st_num}</td>
					<td>${ST.st_name}</td>
					<td>${ST.st_dept}</td>
					<td>${ST.st_grade}</td>
					<td>${ST.st_tel}</td>
					<td>${ST.st_addr}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>