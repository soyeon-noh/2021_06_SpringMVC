package com.callor.jdbc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping( value="/books")
@Controller
public class BookController {

	// localhost:8080/jdbc/books/
	// 끝에 / 가 붙어있거나 붙어있지 않거나 둘다!
	// ""도 포함시켜주지않으면 맨끝에 / 가 안 붙었을 경우 문제가 생긴다.
	@RequestMapping(value={"/",""}, method=RequestMethod.GET)
	public String books() { // controller에서 method 이름은 중요하지x
		
		log.debug("Books Root");
		return "books/list";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert() {
		return "books/input";
	}
}
