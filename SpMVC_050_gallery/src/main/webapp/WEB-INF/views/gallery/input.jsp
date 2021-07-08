<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var = "rootPath" value="${pageContext.request.contextPath}"/>
<style>
@font-face {
     font-family: 'DungGeunMo';
     src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/DungGeunMo.woff') format('woff');
     font-weight: normal;
     font-style: normal;
}

form {
	width: 600px;
	margin: 0 auto;
}

form div {
	margin: 20px; 
}

form label {
	font-family: 'DungGeunMo';
	display: inline-block;
	
	color: white;
	font-size: 25px;
	
	width: 150px;
	text-align: left;
}

form input.title {
	width: 395px;
}

form input {
	padding: 8px;
	background-color: #90ffca;
}

form textarea {
	width: 550px;
	height: 300px;
	resize: none;
	
	background-color: #b1ffda;
}

form div:last-of-type {
	width: 360px;
	margin: 0 auto;
}

form button {
	width: 150px;

	margin: 0 auto;
	padding: 4px;
	
	outline: 0;
	
	font-family: 'DungGeunMo';
	font-size: 20px;
}

form button.reset {
	background-color: #ff3e3e;
	border: 2px solid #ff3e3e;
}

form button.submit {
	background-color: #0089ff;
	margin-right: auto;
	border: 2px solid #0089ff;
}

form button.reset:hover {
	background-color: #FF7878;
}

form button.submit:hover {
	background-color: #37D0FD;
}

input#file-upload-button{
	background-color: #90ffca;
}

input#main_image {
	display: none;
}

input#images {
	display: none;
}

label.button {
	border: 1px solid #90ffca;
	font-size: 20px;
	padding:4px;
	
	text-align: center;
	color: #90ffca;
}

label.button:hover {
	background-color: #90ffca6b;
}

</style>

<form method="POST" enctype="multipart/form-data">

	<div>
		<label>작성자</label>
		<input name="g_writer" value="${CMD.g_writer}">
	</div>
	<div>
		<label>작성일자</label>
		<input name="g_date" value="${CMD.g_date}">
	</div>
	<div>
		<label>작성시각</label>
		<input name="g_time" value="${CMD.g_time}">
	</div>
	<div>
		<label>제목</label>
		<input class="title" name="g_subject">
	</div>
	<div>
		<label></label>
		<textarea name="g_content"></textarea>
	</div>


	<div>
		<label>대표이미지</label>
		<label class="button" for="main_image">파일선택</label>
		<input id="main_image" type="file" name="one_file">
	</div>
	<div>
		<label>갤러리</label>
		<label class="button" for="images">파일선택</label>
		<input id="images" type="file" multiple="multiple" name="m_file">
	</div>
	<!-- file input의 name을 DTO나 VO의 이름으로 쓰지 않는 것이 편하다( 오류 발생 매~우 잦음 ) -->
	
	<div>
		<button type="reset" class="reset">Reset</button>
		<button class="submit">Submit</button>
	</div>
</form>
