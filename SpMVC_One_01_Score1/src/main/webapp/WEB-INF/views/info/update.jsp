<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대한고교 성적처리 - 학생정보 수정</title>
<link href="${rootPath}/static/css/layout.css?ver=2021-06-22-004"
	rel="stylesheet" />
<style>
form.student_update {
	width: 90%;
	margin: 0 auto;
}
form.student_update fieldset {
	border: 1px solid #aaa;
	padding: 0.9rem;
	
	
}

fieldset div {
	padding: 5px;
}

</style>
</head>
<body>
	<div id="container">
		<%@ include file="/WEB-INF/views/include/include_nav.jspf"%>
		<form class="student_update" id="student_update" method="POST">
			<fieldset>
				<div>
					<label>학번</label> <input name="st_num" id="st_num"
						value="${ST.st_num}">
				</div>
				<div>
					<label>이름</label> <input name="st_name" id="st_name"
						value="${ST.st_name}">
				</div>
				<div>
					<label>학과</label> <input name="st_dept" id="st_dept"
						value="${ST.st_dept}">
				</div>
				<div>
					<label>학년</label> <input name="st_grade" id="st_grade"
						value="${ST.st_grade}">
				</div>
				<div>
					<label>전화번호</label> <input name="st_tel" id="st_tel"
						value="${ST.st_tel}">
				</div>
				<div>
					<label>주소</label> <input name="st_addr" id="st_addr"
						value="${ST.st_addr}">
				</div>

				<div class="btn_box">
					<button type="button" class="save">저장</button>
					<button type="reset" class="reset">초기화</button>
					<button type="button" class="list">리스트로</button>
				</div>
			</fieldset>
		</form>
	</div>
</body>
</html>