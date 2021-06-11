<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var = "rootPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
	<%@ include file="/WEB-INF/views/include/include_head.jspf" %>
<style>
	form {
		width: 80%;
		margin: 15px auto;
		
		border: 1px solid green;
		border-radius: 15px;
		padding: 20px;
	}

	
	form label {
		display: inline-block;
		width: 20%;
		
		text-align: right;
		padding: 8px;
		
		color: rgba(0, 0, 255, 0.6);
		font-weight: bold;
	}
	
	form div {
		width: 80%;
		margin: 5px auto;
	}
	
	form input {
		width: 70%;
		padding: 8px;
		border-radius: 20px;
		border-color: rgba(0,255,0,0.5);
		
	}
	
	form input:focus {
		border-color: #999;
	}
	
	form input:hover {
		background-color: #ddd;
	}
	
	
</style>
<body>
	<%@ include file="/WEB-INF/views/include/include_header.jspf" %>
	<h1>출판사 정보 등록</h1>
	<form method="POST"> <!-- anction을 지정하지 않으면 처음 url이 디폴드 -->
		<fieldset>
		<legend>출판사 정보</legend>
		<div>
			<label>출판사명</label>
			<input name="cp_title" id="cp_title">
		</div>
		<div>
			<label>대표자명</label>
			<input name="cp_ceo" id="cp_ceo">
		</div>
		<div>
			<label>전화번화</label>
			<input name="cp_tel" id="cp_tel" type="tel">
		</div>
		<div>
			<label>주소</label>
			<input name="cp_addr" id="cp_addr">
		</div>
		<div>
			<label>주요장르</label>
			<input name="cp_genre" id="cp_genre">
		</div>
		<div class="btn_box">
			<button type="button" class="btn_save comp">저장</button>
			<button type="reset" class="btn_reset comp">다시작성</button>
			<button type="button" class="btn_list comp">돌아가기</button>
		</div>
		</fieldset>
	</form>

	<%@ include file="/WEB-INF/views/include/include_footer.jspf" %>
</body>
<script>
		// const : 상수를 선언하는 키워드
		// 			코드가 진행되는 동안 값이 변경되면 안되는 것
		const doc = document;
		
		doc
			.querySelector("button.btn_delete")
			.addEventListener("click", (e)=>{
				document.querySelector("input[name='cpcode']")
				
				const cpcodeObj = doc
								.querySelector("input#cpcode")
				let cpcode = cpcodeObj.value
				
				// alert("삭제버튼 클릭" + cpcode)
				if(confirm( cpcode + "를 삭제합니다!!")){
					location.replace(
							"${rootPath}/comp/delete?cp_code=" + cpcode 
									
					);
				}
			})
</script>
</html>