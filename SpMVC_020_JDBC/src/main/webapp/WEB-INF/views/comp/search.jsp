<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/include/include_head.jspf"%>

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
						<tr data-ccode="${COMP.cp_code}" class="search_comp">
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
// 아니 이거 작동안하는? 거래는데? 주석때문에 남겨두고 bookinput.jsp에 수정해서 넘김
document.querySelector("section.search_sec table")
	.addEventListener("click",(e)=>{
		
		let target = e.target
		let tagName = target.tagName
		if(tagName === "TD"){ // TD가 클릭되면 
			let ccode = e.target.closest("TR").dataset.ccode 
			alert("출판사 코드" + ccode)
			// TD를 감싸고있는 TR의 data-set에 담긴 ccode 추출
			// 끄집어낼댄 dataset.ccde라고 쓰면된다.
			/*
			table에 click event가 발생하면
			가장 중심부에 있는 TD가 event를 최종 수신한다.
			그러면 TD가 클릭되었을 때와 같은 효과를 낸다.
			
			TD가 클릭되면 어떤 데이터를 가져오고 싶은데
			TD는 여러개가 있어 어떤 TD가 클릭될지 모른다.(코드,저자명..등등)
			1개의 TD에만 데이터를 담아두고 (코드)
			그 TD만 클릭했을 때 반응하도록 하면
			사용자가 상당히 불편할 것이다. (저자명 클릭해도 반응이 없어버림)
			
			그래서 TD가 선택되면(click 되면)
			TD를 감싸고 있는 TR이 누구인지 알아보고
				e.target.closest("TR") : 나를 감싸는 부모중에 TR
			
			closest				
			*/ 
			
		}
		
		//location.href = "${rootPath}/";
});
</script>

</html>