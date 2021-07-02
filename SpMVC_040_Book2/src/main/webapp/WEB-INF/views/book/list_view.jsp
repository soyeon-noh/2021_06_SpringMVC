<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var = "rootPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 홈페이지</title>
<style>
	td.book_title {
		width: 20%;
		max-width: 0;
		text-overflow: ellipsis;
		overflow: hidden;
		text-space: nowrap;
	}
	
	td img {
		padding: 0.5rem;
		border : 1px solid green;
		border-radius: 50%;
		width: 50px;
		height: 50px;
	}
	
</style>
</head>
<body>
	<h1>나의 서재</h1>
	<table id="my_books">
		<tr>
			<td>ISBN</td>
			<td>도서명</td>
			<td>이미지</td>
			<td>출판사</td>
			<td>저자</td>
			<td>출판일</td>
		</tr>
		<c:choose>
			<c:when test="${empty MY_BOOKS}">
				<tr><td colspan="6">데이터 없음</td></tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${MY_BOOKS}" var="BOOK" varStatus="i">
					<tr data-isbn="${BOOK.isbn}">
						<td>${BOOK.isbn}</td>
						<td class="book_title">${BOOK.title}</td>
						<td>
							<img width="50px" 
								src="${BOOK.image}" 
								alt="${BOOK.title}"/>
						</td>
						<td>${BOOK.publisher}</td>
						<td>${BOOK.author}</td>
						<td>${BOOK.pubdate}</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>
</body>
<script>

// script 오류를 방지하기 위한 코드
// table#my_books DOM 요소가 현재 화면에 있으면
let my_books = document.querySelector("table#my_books")
if(my_books){
	my_books.addEventListener("click",(e)=>{
		let td = e.target
		if(td.tagName === "TD"){
			let tr = td.closest("TR")
			let isbn = tr.dataset.isbn
			alert(isbn)
		}
	})
}

</script>
</html>