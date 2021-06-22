document.addEventListener("DOMContentLoaded", () => {
  let modal = document.querySelector("div#modal");
  modal.querySelector("span").addEventListener("click", (e) => {
    modal.style.display = "none";
  });
  document.querySelector("form#book_input").addEventListener("keydown", (e) => {
    // keydown 사용자의 키입력
    let key = e.key;
    let tagName = e.target.tagName;
    let id = e.target.id;

    if (key === "Enter" && tagName === "INPUT") {
      let text = e.target.value; // input box에 넣은 문자열

      if (id === "bk_ccode") {
        fetch(`${rootPath}/comp/search`) // ★ js만을 사용하는 ajax 코드(비동기) 'fetch'
          .then((res) => {
            return res.text(); // response를 통쨰로 가져와서 text로 리턴
          })
          .then((result) => {
            // res의 결과..? html문자열...?
            let div = document.createElement("div"); // js로 div를 추가함
            div.innerHTML = result; // html문자열을 html방식으로 바꿔서 div에 채워넣어라
            modal.appendChild(div);
          });

        // alert("출판사 찾기" + text);
        modal.style.display = "block";
      } else if (id === "bk_acode") {
        // alert("저자찾기" + text);
        modal.style.display = "block";
      }
    }
  });
});
