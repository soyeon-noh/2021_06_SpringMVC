<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/include/include_head.jspf"%>
<style>
	section.search_sec {
		background-color: white;
		opacity: 1;
		width: 80%;
		height: 80%;
		display: flex;
		justify-content: center;
		align-content: center;
	}
</style>
<body>
	<section class="search_sec">
		<table>
			<tr>
				<th>CODE</th>
				<th>출판사명</th>
				<th>대표</th>
				<th>전화번호</th>
				<th>주소</th>
				<th>주요장르</th>
			</tr>
			<c:choose>
				<c:when test="${empty COMPS}">
					<tr>
						<td cospan="6">데이터가 없음</td>
				</c:when>
				<c:otherwise>
					<c:forEach items="${COMPS}" var="COMP" varStatus="seq">
						<tr>
							<td>${COMP.cp_code}</td>
							<td>${COMP.cp_title}</td>
							<td>${COMP.cp_ceo}</td>
							<td>${COMP.cp_tel}</td>
							<td>${COMP.cp_addr}</td>
							<td>${COMP.cp_genre}</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
	</section>

</body>
<script>
document.querySelector("button.btn_insert.comp")
	.addEventListener("click",()=>{
		location.href = "${rootPath}/comp/insert";
});
</script>

</html>