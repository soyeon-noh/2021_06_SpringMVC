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
	<c:forEach items="${MOVIES}" var="MOVIE">
		<div class="content">
			<img src="${MOVIE.image}">
			<div>
				<p class="title">
					<a href="${MOVIE.link}" target="_NEW"> <!-- target 새로운창열기 NEW -->
						${MOVIE.title}
					</a>
				</p>
				<p class="subtitle">${MOVIE.subtitle}</p>
				<p class="director">
					<strong>감독 : </strong>${MOVIE.director}
				</p>
				<p class="acotr">
					<strong>출연배우 : </strong>${MOVIE.actor}
				</p>
				<p class="userRating">
					<strong>평점 : </strong>${MOVIE.userRating}
				</p>
				<button class="insert">내 영화등록</button>
			</div>
		</div>
	</c:forEach>
</body>
</html>