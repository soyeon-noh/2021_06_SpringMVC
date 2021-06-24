document.addEventListener("DOMContentLoaded",()=>{
	
	document.querySelector("table.list").addEventListener("click",(e)=>{
		
		let tagName = e.target.tagName; // tagName 추출
		if(tagName == "TD") { // 클릭한게 TD이면
			let urlPath = rootPath; // JSP의 rootPath 가져옴
			let num = e.target.closest("TR").dataset.num; //  누른 TR의 dataset을 가져옴(h_num)

			location.href = `${urlPath}/info?num=${num}`;	
		}
	});
});
  