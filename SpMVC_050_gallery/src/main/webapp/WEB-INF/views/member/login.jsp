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
	div.msg.view {
		color: yellow;
		background-color: red;
		font-size: 20px;
		padding: 2rem;
	}
	
	form#login_form {
		width: 400px;
		margin: 0 auto;
	}
	
	form#login_form label {
		font-family: 'DungGeunMo';
		display: inline-block;
		
		color: white;
		font-size: 25px;
		
		width: 150px;
		text-align: left;
	}
	
	form#login_form input {
		padding: 8px;
		background-color: #90ffca;
	}
	
	form#login_form div {
		margin: 20px; 
	}
	
	button#btn_login, button#btn_join {
		display: none;
	}
	
</style>

<form method="POST" id="login_form">
	<div class="msg login error"></div>
	<div>
		<label>사용자 ID</label>
		<input name="m_userid" type="email">
	</div>
	<div>
		<label>비밀번호</label>
		<input name="m_password" type="password">
	</div>
	<div>
		<label></label>
		<label class="button" for="btn_login">login</label>
		<button type="button" class="login" id="btn_login">로그인</button>
		<label></label>
		<label class="button" for="btn_join">join</label>
		<button type="button" class="join" id="btn_join">회원가입</button>
	</div>
</form>

<script>
let form = document.querySelector("#login_form") // id로 되어있는 건 tagname 안써도됨

let btn_login = document.querySelector("button.login")
let btn_join = document.querySelector("button.join")
let msg_error = document.querySelector("div.msg.login.error")

let input_userid = document.querySelector("input[name='m_userid']") // input box 그자체
let input_password = document.querySelector("input[name='m_password']")


// 유효성검사
if(btn_login){
	
	btn_login.addEventListener("click",()=>{

		let m_userid = input_userid.value // inpu box에 담긴 값!
		let m_password = input_password.value
		
		if(m_userid === ""){
			alert("사용자 ID는 필수입력 항목입니다 !!!")
			input_userid.focus()
			return false
		}
		if(m_password === ""){
			alert("비밀번호는 필수입력 항목입니다 !!!")
			input_password.focus()
			return false
		}
		
		form.submit()
	})
	
}

if(btn_join){
	btn_join.addEventListener("click",()=>{
		location.href="${rootPath}/member/join"
	})
}


// 로그인 실패
let login_fail = "${LOGIN_FAIL}"

if(login_fail == "NOT_USERID"){
	
	msg_error.innerText = "사용자 ID가 없습니다!!!"
	msg_error.classList.add("view")
	
	//msg_error.style.fontSize = "20px"
	//msg_error.style.backgroundColor = "red"
	//msg_error.style.padding = "2rem"
	
} else if(login_fail == "NEQ_PASS"){
	
	msg_error.innerText = "비밀번호가 틀렸습니다!!!"
	msg_error.classList.add("view")
		
		//msg_error.style.fontSize = "20px"
		//msg_error.style.backgroundColor = "red"
		//msg_error.style.padding = "2rem"
} else if(login_fail === "LOGIN_REQ"){
	
	msg_error.innerHTML = "로그인이 필요한 서비스 입니다.<br/>";
	msg_error.innerHTML += "로그인을 해주세요.";
	msg_error.classList.add("view")
		
}





</script>