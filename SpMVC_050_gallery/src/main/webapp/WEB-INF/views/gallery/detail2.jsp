<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var = "rootPath" value="${pageContext.request.contextPath}"/>

<div id="gallery_info">
	<h3>제목 : ${GALLERY.g_subject}</h3>
	<h5>SEQ : ${GALLERY.g_seq}</h5>
	<p>작성일 : ${GALLERY.g_date}</p>
	<p>작성시각 : ${GALLERY.g_time}</p>
	<p>작성자 : ${GALLERY.g_writer}</p>
	<p>내용 : ${GALLERY.g_content}</p>
</div>

<style>
	div#gallery_files {
		display: flex;
		flex-wrap: wrap;
	}
	div#gallery_files img {
		margin: 2px;
	}
</style>
<c:choose>
	<c:when test="${not empty GFLIST}">
	<div id="gallery_files">
		<c:forEach items="${GALLERY.fileList}" var="FILE">
			<img src="${rootPath}/files/${FILE.file_upname}" height="100px"/>
		</c:forEach>
	</div>
	</c:when>
	<c:otherwise>
		<p>사진이 없습니다.</p>
	</c:otherwise>
</c:choose>
<div>
	<button type= "button" class="gallery update">수정</button>
	<button type= "button"  class="gallery delete">삭제</button>
</div>

<script>
let update_button = document.querySelector("button.gallery.update")
let delete_button = document.querySelector("button.gallery.delete")

update_button.addEventListener("click",()=>{
	alert("일련번호 ${GFLIST[0].g_seq} 인 게시물을 수정")
	location.href="${rootPath}/gallery/update"
					+ "?g_seq=${GALLERY.g_seq}"
})

delete_button.addEventListener("click",()=>{
	if(confirm("일련번호 ${GFLIST[0].g_seq} 인 게시물을 삭제합니까?")){
		location.replace("${rootPath}/gallery/delete"
						+ "?g_seq=${GALLERY.g_seq}")
	}
})

</script>

