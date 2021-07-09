<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var = "rootPath" value="${pageContext.request.contextPath}"/>
<style>
@font-face {
    font-family: 'IBMPlexSansKR-Regular';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/IBMPlexSansKR-Regular.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

	div.container {
		display: flex;
		flex-wrap: wrap;
		
		justify-content: space-around;
		align-items: flex-start;
		
		width: 1400px;
		margin: 0 auto;

		border: 1px solid orange;
	}
	div.ga_box {
		display: flex;
		flex-direction: column;
		flex: 1;
		
		padding: 20px;
		
		flex-basis: 600px;
		width: 600px;
		height: 500px;
		
		border: 1px solid orange;
	}
	
	div.ga_box div {
		margin: 0 auto;
		font-family: 'IBMPlexSansKR-Regular';

	}
	
	div.ga_box div:first-of-type {
		flex: 1;
		color: #FC00FF;
	}
	div.ga_box div:last-of-type {
		flex: 3;
		width: 300px;
		color: white;
	}
	
	div.ga_box a {
		text-decoration: none;
		color: #27D4B6; 
	}
	
	div.ga_box a:hover {
		text-decoration: none;
		color: #FF0081;
	}
</style>

<div class="container">
<c:forEach items="${GALLERYS}" var="GALLERY">

	<div class="ga_box">
		<div>
			<c:if test="${empty GALLERY.g_image}">
				<img src="${rootPath}/files/noimage.png" width="80%">
			</c:if>
			<c:if test="${not empty GALLERY.g_image}">
				<img src="${rootPath}/files/${GALLERY.g_image}" width="500px">
			</c:if>
		</div>
		<div>
			<h3>
				<a href="${rootPath}/gallery/detail2/${GALLERY.g_seq}">
					${GALLERY.g_subject}
				</a>
			</h3>
			<p>${GALLERY.g_content}</p>
		</div>
	</div>
</c:forEach>
</div>

