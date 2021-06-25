<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대한 고교 성적처리 - 성적알람표</title>
<link href="${rootPath}/static/css/layout.css?ver=2021-06-22-004"
	rel="stylesheet" />
<style>

	input {
		padding: 4px;
		width: 140px;
	}

	div.view.student {
		display: flex;
		width: 90%;
		margin: 0 auto;
	}
	
		table.list.student {
			flex:1;

		}
		
		table.list.student tr {
			border-top: 1px solid #999;
			border-bottom: 1px solid #999;
		}
	
		button.student.update {
			flex: 0.2;
			display: inline-block;
			margin: 25px 0 25px 25px;
		}
		

	form.score_input {
		width: 90%;
		margin: 0 auto;
	}

		fieldset {
			border: 1px solid #aaa;
			padding: 0.9rem;
			display: flex;
	
		}
		
		fieldset div {
			padding: 0 8px;
		}
		
		fieldset div.title {
			margin-right: auto;
			font-weight: bold; 
		}
		
		form label, form input {
			display: inline-block;
			margin: 0 3px;
		}
		
		
		button.score.insert {
			padding: 2px;
		}
</style>
</head>
<body>
	<div id="container">
		<%@ include file="/WEB-INF/views/include/include_nav.jspf"%>
		<h1 class="title">성적알람표</h1>
		<div class = "view student">
		<table class="list student">
			<tr>
				<th>학번</th>
				<td>${IF.h_num}</td>
				<th>전공</th>
				<td>${IF.h_dept}</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>${IF.h_name}</td>
				<th>학년</th>
				<td>${IF.h_grade}</td>
			</tr>
		</table>	
		<button type="button" class="student update">학생정보 수정</button>
		</div>
		
		<form class = "score_input" id="score_input" method="POST">
			<fieldset>
				<div class="title">
					<label>성적추가</label>
				</div>
				<div>
					<input name="sc_stnum" id="sc_stnum" value="${IF.h_num}"
					type="hidden"/>
				</div>
				<div>
					<label>과목명</label>
					<input name="sc_subject" id="sc_subject" placeholder="과목명"/>
				</div>
				<div>
					<label>점수</label>
					<input name="sc_score" id="sc_score" placeholder="점수"/>
				</div>
				<button type="button" class="score insert">추가하기</button>
			</fieldset>
		</form>


		<table class="list score">
			<tr>
				<th>No.</th>
				<th>과목명</th>
				<th>점수</th>
			</tr>				
			<c:forEach items="${SCORES}" var="SC" varStatus="index">
			
				<tr>
					<td>${index.count}</td>
					<td>${SC.sc_subject}</td>
					<td>${SC.sc_score}</td>
				</tr>
			</c:forEach>
				<tr>
					<td>총점</td>
					<td>${IF.h_count}</td>
					<td>${IF.h_sum}</td>
				</tr>
		</table>


	</div>
</body>
<script>
	document.querySelector("button.student.update").addEventListener("click", (e)=>{
		location.href = "${rootPath}/info/update"
	})

	document.querySelector("button.score.insert").addEventListener("click", (e)=>{
		
		let sc_stnum = document.querySelector("input#sc_stnum")
		let sc_subject = document.querySelector("input#sc_subject")
		let sc_score = document.querySelector("input#sc_score")
		
		if(sc_stnum.value === ""){
			alert("학번을 입력해주세요")
			sc_stnum.focus()
			return false
		}
		
		if(sc_subject.value === ""){
			alert("과목명을 입력해주세요")
			sc_subject.focus()
			return false
		}
		
		if(sc_score.value === ""){
			alert("점수를 입력해주세요")
			sc_score.focus()
			return false
		}
		
		document.querySelector("form#score_input").submit();
		
	})
</script>
</html>