<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />

<h1>성적List</h1>
<table>
	<tr>
		<th>No.</th>
		<th>학번</th>
		<th>이름</th>
		<th>과목코드</th>
		<th>과목명</th>
		<th>점수</th>
	</tr>

	<c:choose>
		<c:when test="${empty SCLIST}">
			<tr>
				<td colspan="5">데이터가 없음</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach items="${SCLIST}" var="SC">
				<tr>
					<td>${SC.sc_seq}</td>
					<td>${SC.sc_stnum}</td>
					<td>${SC.sc_stname}</td>
					<td>${SC.sc_sbcode}</td>
					<td>${SC.sc_sbname}</td>
					<td>${SC.sc_score}</td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</table>
