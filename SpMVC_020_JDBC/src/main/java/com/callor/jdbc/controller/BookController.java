package com.callor.jdbc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.callor.jdbc.model.UserVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(value = "/books")
@Controller
public class BookController {

	// localhost:8080/jdbc/books/
	// 끝에 / 가 붙어있거나 붙어있지 않거나 둘다!
	// ""도 포함시켜주지않으면 맨끝에 / 가 안 붙었을 경우 문제가 생긴다.
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String books(HttpSession hSession, Model model) { // controller에서 method 이름은 중요하지x

		// HttpSession에서 USERVO 정보 찾기
		Object obj = hSession.getAttribute("USERVO");
		UserVO userVO = (UserVO) obj;

		// USERVO 정보가 없으면
		if (userVO == null) {
			// Login 화면으로 jump
			model.addAttribute("MSG", "LOGIN");
			return "redirect:/member/login";
		}

		log.debug("Books Root");
		return "books/list";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert() {
		return "books/input";
	}
}
