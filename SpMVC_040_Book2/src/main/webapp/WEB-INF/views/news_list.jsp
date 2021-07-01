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
	<c:forEach items="${NEWS_LIST}" var="NEWS">
		<div class="content">
			<div>
				<p class="title">
					<a href="${NEWS.link}" target="_NEW"> <!-- target 새로운창열기 NEW -->
						${NEWS.title}
					</a>
				</p>
				<p class="desc">${NEWS.description}</p>
				<p class="naver">
					<a href = "${NEWS.link}" target="_NEW">
					자세히보기
					</a>
				</p>
				<p class="origin">
					<a href = "${NEWS.originallink}" target="_NEW">
					언론사바로가기
					</a>
				</p>
				<button class="insert">내 서재등록</button>
			</div>
		</div>
	</c:forEach>
</body>
</html>