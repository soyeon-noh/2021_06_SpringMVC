document.addEventListener("DOMContentLoaded", () => {
  const nav = document.querySelector("nav#main_nav");

  nav.addEventListener("click", (e) => {
    let tagName = e.target.tagName;

    if (tagName === "LI") {
      let menuText = e.target.textContent;

      // `` backTit : 역 작은 따옴표
      // JS에서 변수를 포함하는 문자열을 생성할 때 사용한다.
      // let urlPath = rootPath; 로 쓰면 되는건데 그냥 백팃한번 써봤다!
      let urlPath = `${rootPath}`; // JSP에서 만든 <script> 속의 var rootPath 변수!!

      if (menuText === "HOME") {
        // location.href = "${rootPath}/"; 이걸 아래처럼 변경

        //urlPath += rootPath + "/"
        urlPath += "/";
      } else if (menuText === "도서정보") {
        // location.href = "${rootPath}/books"; 이걸 아래처럼 변경
        urlPath += "/books";
      } else if (menuText === "출판사정보") {
        urlPath += "/comp";
      } else if (menuText === "저자정보") {
        urlPath += "/author";
      } else if (menuText === "로그인") {
        urlPath += "/member/login";
      } else if (menuText === "회원가입") {
        urlPath += "/member/join";
      }

      alert(`내가 가야할 곳 ${urlPath}`); // 백팃을 쓰는 것은 문자열에 변수를 쓰기 위해서!
      location.href = urlPath;
    }
  });
});
