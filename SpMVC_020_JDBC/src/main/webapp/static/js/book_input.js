document.addEventListener("DOMContentLoaded", () => {
  let modal = document.querySelector("div#modal");
  modal.querySelector("span").addEventListener("click", (e) => {
    // 임의로 생성된 div_search Box는
    // modal과 별개로 생성하였으므로
    // div_search box를 remove하고 modal을 감춘다

    document.querySelector("div#div_search").remove();
    modal.style.display = "none";
  });
  document.querySelector("form#book_input").addEventListener("keydown", (e) => {
    // keydown 사용자의 키입력
    let key = e.key;
    let tagName = e.target.tagName;
    let id = e.target.id;
    let className = e.target.className;

    if (key === "Enter" && tagName === "INPUT") {
      let text = e.target.value; // input box에 넣은 문자열
      let urlPath = rootPath;

      if (id === "bk_ccode") {
        urlPath += `/comp/search?cp_title=${text}`;
      } else if (id === "bk_acode") {
        urlPath += `/author/search?au_name=${text}`;
      }

      if (className === "search") {
        modal.style.display = "block";
        fetch(urlPath) // ★ js만을 사용하는 ajax 코드(비동기) 'fetch'
          .then((res) => {
            return res.text(); // response를 통쨰로 가져와서 text로 리턴
          })
          .then((result) => {
            // res의 결과..? html문자열...?
            // 새로운 element(tag)
            let div = document.createElement("div"); // js로 div를 추가함
            // 본문 내용 추가
            div.innerHTML = result; // html문자열을 html방식으로 바꿔서 div에 채워넣어라
            // id 추가
            div.setAttribute("id", "div_search");
            document.querySelector("body").appendChild(div); //문제가 생겨서 원래 모달위에 div를 만들었는데 body로바꿈.
            // 이전코드는 modal.이었나..??
          });
      }
    }
  });

  document
    .querySelector("form#book_input button.book")
    .addEventListener("click", (e) => {
      let form = document.querySelector("form#book_input");
      let bk_isbn = form.querySelector("input#bk_isbn");
      let bk_title = form.querySelector("input#bk_title");
      let bk_ccode = form.querySelector("input#bk_ccode");
      let bk_acode = form.querySelector("input#bk_acode");
      let bk_price = form.querySelector("input#bk_price");
      let bk_pages = form.querySelector("input#bk_pages");

      // front 단에서 유효성 검사
      if (bk_isbn.value === "") {
        alert("ISBN은 반드시 입력하세요.");
        bk_isbn.focus();
        return false; // 더이상진행하지말라
      }
      if (bk_isbn.value.length != 13) {
        alert("ISBN은 13자리 이어야 합니다.");
        bk_isbn.focus();
        return false;
      }

      if (bk_title.value === "") {
        alert("도서명은 반드시 입력하세요.");
        bk_title.focus();
        return false;
      }

      // 더 추가하기

      // 여기 도착하면 유효성검사 모두 통과
      // form에 담긴 데이터를 서버로 req 하는데
      // front에서는 submit 한다고 표현한다.
      form.submit(); // 서버로 전송된다.
    });
});
