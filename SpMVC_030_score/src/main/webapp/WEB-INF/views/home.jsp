<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 홈페이지</title>
</head>
<body>
	<h1>대한고교 성적처리</h1>
	<c:choose>
		<!-- 각 Controller의 method마다 BODY key에 문자열넣고
	그에따라서 원하는 화면을 home에서 include로 다 보여주어
	footer나 nav를 여기저기 다 붙일 필요가 없게 만든다. -->
		<c:when test="${BODY eq 'SCORE_VIEW'}">
			<%@ include file="/WEB-INF/views/score/list.jsp"%>
		</c:when>
		<c:otherwise>
			<%@ include file="/WEB-INF/views/main.jsp"%>
		</c:otherwise>
	</c:choose>
</body>
</html>