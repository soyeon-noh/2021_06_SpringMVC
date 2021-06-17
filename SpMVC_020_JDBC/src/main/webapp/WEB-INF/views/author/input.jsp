<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var = "rootPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/include/include_head.jspf" %>

<body>
	<%@ include file="/WEB-INF/views/include/include_header.jspf" %>
	<section class="main_sec">
	<form method="POST">
		<fieldset>
		<legend>저자 정보 등록</legend>
		<div>
			<label>CODE</label>
			<input name="au_code" id="au_code" placeholder=""/>
		</div>
		<div>
			<label>저자명</label>
			<input name="au_name" id="au_name" placeholder=""/>
		</div>
		<div>
			<label>전화번호</label>
			<input name="au_tel" id="au_tel" placeholder=""/>
		</div>
		<div>
			<label>주소</label>
			<input name="au_addr" id="au_addr" placeholder=""/>
		</div>
		<div>
			<label>주요장르</label>
			<input name="au_genre" id="au_genre" placeholder=""/>
		</div>
		</fieldset>
	<div class="btn_box">
		<button type="button" class="btn_save author">저장</button>
		<button type="reset" class="btn_reset author">새로작성</button>
		<button type="button" class="btn_list author">돌아가기</button>
		<!-- button type을 button으로하면 전송을 막는다.
		스크립트로 처리할 수 있게.
		reset으로하면 input에 적은 내용을 전부 초기화한다. -->
	</div>
	</form>
	</section>

	<%@ include file="/WEB-INF/views/include/include_footer.jspf" %>
</body>
<script>
document.querySelector("button.btn_book_insert")
	.addEventListener("click",()=>{
		
		location.href = "${rootPath}/books/insert";
		
	});
</script>
</html>