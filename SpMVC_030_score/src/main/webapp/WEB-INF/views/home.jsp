<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale:1">
<title>나의 홈페이지</title>
<style>
* {
	box-sizing: border-box;
	margin:0;
	padding:0;
}

body {
	/* viewpoint의 width만큼 */
	width: 100wv;
	height: 100vh;
	display: flex;
	flex-direction: column;
}

header {
	background: url("${rootPath}/static/images/header_background.jpg")
		no-repeat;
	background-size: 100% 50%;
	background-position: top; /* 이게뭘까 */
	background-attachment: fixed;
	
	text-align: center;
	text-shadow: 1px 1px 1px black;
	color: white;
	padding: 2rem;

	/*height: 30vh;가 없다면 2rem으로 표시했을 때 중앙으로 정렬되게 할 수 있음*/
}

section#main_sec {
	flex: 1;
	width: 100wv;
	display: flex;
	flex-direction: column;
	/*버튼이 아래로 배치될 수 있게 설정하는 부분*/
	background: url("${rootPath}/static/images/section_background.jpg")
		no-repeat;
	background-size: 100% 100%;
	background-attachment: fixed;
}

table {
	border: 0;
	width: 90%;
	border-collapse: collapse;
	border-spacing: 0;
	margin: 10px auto;
}

tr {
	border-top: 1px solid green;
}

tr:last-child {
	border-bottom: 1px solid green;
}

tr:nth-of-type(odd) {
	background-color: #ccc;
}

tr:nth-of-type(even) {
	background-color: #eee;
}

tr:hover td {
	background-color: #aaa;
	cursor: pointer;
}

td, th {
	border-right: 1px solid green;
	padding: 8px 12px;
	text-align: center;
}

td.number {
	text-align: right;
}

td:last-child, th:last-child {
	border: none;
}

div.btn_box {
	width: 90%;
	/* 
	table의 margin: 10px auto로 설정되어 있기 때문에 
	상단 margin은 0으로 bottom margin은 10px로
	좌우는 auto로 설정했다.
	 */
	margin: 0px auto 10px auto;
	padding: 5px;
	text-align: right;
}

div.btn_box button {
	border: 0;
	/*outline: 0; 버튼을 눌렀을 때 테두리가 남게 하지 않기 위함*/
	outline: 0;
	padding: 12px 16px;
	margin-left: 10px;
	border-radius: 5px; /*숫자맞나?*/
}

button:hover {
	box-shadow: 2px 2px 2px 2px black;
	cursor: pointer;
}
</style>
</head>
<body>
	<header>
		<h1>대한고교 성적처리</h1>
		<p>대한고교 성적처리 시스템 2021</p>
	</header>
	<section id="main_sec">
		<!-- 각 Controller의 method마다 BODY key에 문자열넣고
	그에따라서 원하는 화면을 home에서 include로 다 보여주어
	footer나 nav를 여기저기 다 붙일 필요가 없게 만든다. -->
		<c:choose>
			<c:when test="${BODY eq 'SCORE_VIEW'}">
				<%@ include file="/WEB-INF/views/score/list.jsp"%>
			</c:when>
			<c:otherwise>
				<%@ include file="/WEB-INF/views/main.jsp"%>
			</c:otherwise>
		</c:choose>
	</section>
</body>
<script>
document.querySelector("button.student.insert")
	.addEventListener("click",(e)=>{
		location.href = "${rootPath}/student"
})
</script>

</html>