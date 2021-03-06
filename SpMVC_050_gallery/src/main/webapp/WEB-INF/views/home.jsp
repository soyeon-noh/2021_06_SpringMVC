<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var = "rootPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GALLERY SOYEON</title>
<script src="https://kit.fontawesome.com/6c687ea38d.js" crossorigin="anonymous"></script>
<style>
*{
	box-sizing: border-box;
	margin: 0;
	padding: 0;

}

@font-face {
    font-family: 'KoreanFrenchTypewriter';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2102-01@1.0/KoreanFrenchTypewriter.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

@font-face {
     font-family: 'DungGeunMo';
     src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/DungGeunMo.woff') format('woff');
     font-weight: normal;
     font-style: normal;
}

@font-face {
    font-family: 'SDSamliphopangche_Outline';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts-20-12@1.0/SDSamliphopangche_Outline.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

@font-face {
    font-family: 'IBMPlexSansKR-Regular';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/IBMPlexSansKR-Regular.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

body {
	/*background-color: #271D23;*/
	
	/*background: linear-gradient(-45deg, #ee7752, #e73c7e, #23a6d5, #23d5ab);*/ 
	background: linear-gradient(-45deg, #271D23, #2D2929, #3D144C ,#2A4454);
    background-size: 400% 400%; 
    animation: AnimationName 10s ease infinite;
}

h1.title {
	color: #FF0081; 
	font-family: 'DungGeunMo';
	font-size: 100px;
	text-shadow: -2px 0 white, 0 2px white, 2px 0 white, 0 -2px white;
	text-align: center;
}

nav {
	color: white;
}

nav.main_nav {
	font-family: 'DungGeunMo';
	text-shadow: -1px 0 white, 0 1px white, 1px 0 white, 0 -1px white;
	font-size: 40px;
}

nav.main_nav ul {
	display: flex;
	justify-content: space-around;
	list-style: none;

}

nav.main_nav ul li {
	flex: 1;
	text-align: center;
	margin: 20px;

}

nav.main_nav a {
	text-decoration: none;
	color: #8B03FF; 
}


nav.main_nav a:visited {
	text-decoration: none;
}

nav.main_nav a:link {
	text-decoration: none;
}

nav.main_nav a:hover {
	text-decoration: none;
	color: #61B3E7;
}



@-webkit-keyframes AnimationName { 0%{background-position:0% 50%} 50%{background-position:100% 50%} 100%{background-position:0% 50%} }

</style>
</head>
<body>
	<h1 class="title">GALLERY SOYEON</h1>
	
	<%@ include file="/WEB-INF/views/include/include_nav.jspf" %>
	
	<nav class="main_nav">
		<ul>
			<li>
				<a href="${rootPath}/gallery">????????????</a>
			</li>
			<li>
				<a href="${rootPath}/gallery/input">????????? ??????</a>
			</li>
		</ul>
	</nav>
	
	<c:choose>
		<c:when test="${BODY eq 'GA-INPUT'}">  <!-- ???????????? == ??? ????????? eq??? ?????? -->
			<%@ include file="/WEB-INF/views/gallery/input.jsp" %>
		</c:when>
		<c:when test="${BODY eq 'GA-LIST'}">
			<!-- <a href="${rootPath}/gallery/input">????????? ??????</a> -->
			<%@ include file="/WEB-INF/views/gallery/list.jsp" %>
		</c:when>
		<c:when test="${BODY eq 'GA-DETAIL'}">
			<!-- <a href="${rootPath}/gallery">????????????</a>  -->
			<%@ include file="/WEB-INF/views/gallery/detail.jsp" %>
		</c:when>
		<c:when test="${BODY eq 'GA-DETAIL-V2'}">
			<%@ include file="/WEB-INF/views/gallery/detail2.jsp" %>
		</c:when>
		<c:when test="${BODY eq 'JOIN'}">
			<%@ include file="/WEB-INF/views/member/join.jsp" %>
		</c:when>
		<c:when test="${BODY eq 'LOGIN'}">
			<%@ include file="/WEB-INF/views/member/login.jsp" %>
		</c:when>
		
		<c:otherwise>
			<a href="${rootPath}/gallery/input">????????? ??????</a>
		</c:otherwise>
	</c:choose>
	
	<c:forEach items="${FILES}" var="FILE">
		<a href="${rootPath}/files/${FILE}" target="_NEW">
			<img src="${rootPath}/files/${FILE}" 
				width="100px" height="100px"/>
		</a>
	</c:forEach>	
</body>

<script>
let main_nav = document.querySelector("nav#main_nav")

// main nav??? ?????????!
if(main_nav){
	
	main_nav.addEventListener("click", (e)=>{
		let menu = e.target
		if(menu.tagName === "LI") { // li tag?????? ???????????? ????????????
			
			if(menu.id === "join"){ // menu??? id??? join?????? 
				location.href = "${rootPath}/member/join" //???????????? ???????????? ????????? join????????? ???????????? ????????????
			} else if(menu.id === "login"){
				location.href = "${rootPath}/member/login/nav"
			} else if(menu.id === "logout"){
				location.href = "${rootPath}/member/logout"
			} else if(menu.id === "image_create"){
				location.href = "${rootPath}/gallery/input"
			} else if(menu.id === "home"){
				location.href = "${rootPath}/" // ?????? "/" ????????? ???????????? ?????? x. ????????? ???????????????.
			}
		}
		
	})
	
}



</script>
</html>

















