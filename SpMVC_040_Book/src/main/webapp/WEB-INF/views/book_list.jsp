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
	<c:forEach items="${BOOKS}" var="BOOK">
		<div class="content">
			<img src="${BOOK.image}">
			<div>
				<p class="title">
					<a href="${BOOK.link}" target="_NEW"> <!-- target 새로운창열기 NEW -->
						${BOOK.title}
					</a>
				</p>
				<p class="desc">${BOOK.description}</p>
				<p class="author">
					<strong>저자 : </strong>${BOOK.author}
				</p>
				<p class="publisher">
					<strong>출판사 : </strong>${BOOK.publisher}
				</p>
				<button class="insert">내 서재등록</button>
			</div>
		</div>
	</c:forEach>
</body>
</html>