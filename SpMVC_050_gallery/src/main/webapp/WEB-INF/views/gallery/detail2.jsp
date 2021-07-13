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
div.container {
	font-family: 'DungGeunMo';
	width: 500px;
	height: 600px;
	border: 5px solid #59e0a0;
	
	padding: 20px;
	margin: 0 auto;
	
	background-color: #14121b;
	
}

div#gallery_info h3 {
	
	color: #59e0a0;
	
	text-align: center;
	font-size: 28px;
	
}

div#gallery_info h5 {
	
	color: white;
	text-align: center;
	margin-bottom: 10px;
}

div#gallery_info p {
	
	color: white;
	text-align: right;
}

div#gallery_under {
	
	margin:	20px 0; 
	

}

/* 이거 쓰라해서 썼는데 나랑 화면달라서 의미가 없음 */
	div#gallery_files div.gallery_file {
		width: 200px;
		height: 200px;
		padding: 5px;
		overflow: hidden;
		position: relative;
	}
	div.gallery_file:after {
		content: "삭제";
		position: absolute;
		left: 0;
		top: 0;
		bottom: 0;
		right: 0;
		background-color: transparent;
		color: transparent;
		z-index: 10;
		transition: 1s;
	}
	div.gallery_file:hover:after {
		background-color: rgba(0,0,0,0.7);
		color: white;
		text-align: center;
		vertical-align: middle;
		cursor: pointer;
	}
	
	

	div#gallery_files {
		display: flex;
		flex-wrap: wrap;
	}
	
	div#gallery_files img {
		margin: 2px;
		flex: 1;
	}

</style>
<div class="container">
	<div id="gallery_info">
		<h3>제목 : ${GALLERY.g_subject}</h3>
		<h5>SEQ : ${GALLERY.g_seq}</h5>
		<p>작성일 : ${GALLERY.g_date}</p>
		<p>작성시각 : ${GALLERY.g_time}</p>
		<p>작성자 : ${GALLERY.g_writer}</p>
		<p>내용 : ${GALLERY.g_content}</p>
	</div>
	
	<div id="gallery_under">	
		<c:choose>
			<c:when test="${not empty GALLERY}">
			<div id="gallery_files">
				<c:forEach items="${GALLERY.fileList}" var="FILE">
					<div class="gallery_file" data-fseq="${FILE.file_seq}"> <!-- 이 div만들었음.. -->
					<c:if test="${empty FILE.file_upname}">
						<img src="${rootPath}/files/noImage.png" height="100px"/>
					</c:if>
					<c:if test="${not empty FILE.file_upname}">
						<img src="${rootPath}/files/${FILE.file_upname}" height="100px" data-fseq="${FILE.file_seq}"/> 
						<!-- 여기 높이 100 지우라는데 나랑 화면이 달라서 나는 일단넣어둠.. -->
					</c:if>
					</div>
				</c:forEach>
			</div>
			</c:when>
			<c:otherwise>
				<p>사진이 없습니다.</p>
			</c:otherwise>
		</c:choose>
			
		<div>
			<button type= "button" class="gallery update">수정</button>
			<button type= "button" class="gallery delete">삭제</button>
		</div>
	</div>
</div>




<script>
let update_button = document.querySelector("button.gallery.update")
let delete_button = document.querySelector("button.gallery.delete")

update_button.addEventListener("click",()=>{
//	alert("일련번호 ${GFLIST[0].g_seq} 인 게시물을 수정")

/*
	location.href = "${rootPath}/gallery/update?g_seq=" + ${GALLERY.g_seq}
	${GALLERY.g_seq}가 문자열일 경우 위의 코드는 실행되지 않는다. 
	(여기서는 숫자라 됐지만... 문자열이면 스크립트 연결이 안됨!!!)
*/
    
/*
	현재 GALLERY.g_seq 값은 숫자형이다
	만약 GALLERY.g_seq 값니 S0001 등과 같이 문자열형이라면 이 코드는 JS 문법오류가 발생할 것이다
	
	WHY...?
	
	현재 작성한 코드는 JSP 코드이다
	이 코드는 Response가 될 때 HTML 코드로 변환이 되고 Script 부분도 변환이 된다.
	       
	변환되는 과정에서 ${GALLERY.g_seq}는 담겨있는 문자열인 S0001만 단독으로 나타난다
	위의 코드는 "/root-context/gallery/update?g_seq=" + S0001처럼 변환이 된다
	결국 JS 코드가 실행될 때 +S0001 처럼 변환되어 변수를 찾게 된다
	그리고 S0001이라는 변수가 정의되지 않아 문법오류가 발생한다
	
	** EL tag를 사용하여 스크립트 부분에서 직접 값을 부착할 때는 DO("")를 부착하여 문법 오류를 방지하자! **
*/


	location.href="${rootPath}/gallery/update"
					+ "?g_seq=${GALLERY.g_seq}"
})

delete_button.addEventListener("click",()=>{
	if(confirm("일련번호 ${GFLIST[0].g_seq} 인 게시물을 삭제합니까?")){
		// .replace -- 뒤로가기 안 됨..
		location.replace("${rootPath}/gallery/delete"
						+ "?g_seq=${GALLERY.g_seq}")
	}
})


// 3교시 : 여기쓰고있음... 
let gallery_files = document.querySelector("div#gallery_files")
if(gallery_files){
	gallery_files.addEventListener("click", (e)=> {
		
		let tag = e.target
		alert(tag.tagName)
		if(tag.tagName === "DIV" && tag.className.includes("gallery_file")){
	//머지.. 나는 IMG뜨는데 선생님은 DIV가뜬다면서 위에처럼 하심
			let seq = tag.dataset.fseq
			if(confirm(seq+"이미지 삭제")){
				
				fetch("${rootPath}/gallery/file/delete/" + seq)
				.then( response->response.text())
				.then(result=>{
					if(result === "OK"){
						alert("삭제성공")
					} else if(result ==="NO"){
						alert("서버가 모른대")
					} else {
						alert("삭제 실패")
					}
				})
			}
		}
	})
}


</script>

