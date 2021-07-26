let button1 = document.querySelector("button#json_1");

if (button1) {
  button1.addEventListener("click", (e) => {
    // json type의 데이터를 만들고
    const jsonData = {
      user_id: "hong@naver.com",
      name: "Hong",
      tel: "010-111-1111",
      age: 33,
    }; // jsonData

    fetch(`${rootPath}/json/dto`, {
      method: "POST",
      headers: {
        "content-Type": "application/json;char=UTF8",
      },
      // JSON 데이터를 문자열화 (Serialize)
      body: JSON.stringify(jsonData),
    })
      .then((res) => res.text())
      .then((result) => alert(result));
  });

  let button2 = document.querySelector("button#json_2");

  if (button2) {
    button1.addEventListener("click", (e) => {
      // json type의 데이터를 만들고
      const jsonData = {
        user_id: "hong@naver.com",
        name: "Hong",
        tel: "010-111-1111",
        age: 33,
      }; // jsonData

      const jsonList = {
        name: "홍길동",
        취미들: [{ 취미: "등산" }, { 취미: "영화감상" }],
      };

      fetch(`${rootPath}/json/dto`, {
        method: "POST",
        headers: {
          "content-Type": "application/json;char=UTF8",
        },
        // JSON 데이터를 문자열화 (Serialize)
        body: JSON.stringify({ user_id: "callor" }),
      })
        .then((res) => res.text())
        .then((result) => alert(result));
    });
  }
}
