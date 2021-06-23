<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대한 고교 성적처리 - 성적알람표</title>
<link href="${rootPath}/static/css/layout.css?ver=2021-06-22-004"
	rel="stylesheet" />
</head>
<body>
	<div id="container">
		<%@ include file="/WEB-INF/views/include/include_nav.jspf"%>
		<h1 class="title">성적알람표</h1>
		<table class="list">
			<tr>
				<th>학번</th>
				<th>과목명</th>
				<th>점수</th>
			</tr>
			<c:forEach items="${SCLIST}" var="SC">
				<tr data-num="${SC.sc_seq}">
					<td>${SC.sc_stnum}</td>
					<td>${SC.sc_subject}</td>
					<td>${SC.sc_score}</td>
				</tr>
			</c:forEach>
		</table>
		
		<table>
			<tr>
				<th>No.</th>
				<th>과목</th>
				<th>점수</th>
			</tr>
						<c:forEach items="${SCLIST}" var="SC">
				<tr data-num="${SC.sc_seq}">
					<td>${SC.sc_stnum}</td>
					<td>${SC.sc_subject}</td>
					<td>${SC.sc_score}</td>
				</tr>
			</c:forEach>
		
		</table>
	</div>
</body>
</html>