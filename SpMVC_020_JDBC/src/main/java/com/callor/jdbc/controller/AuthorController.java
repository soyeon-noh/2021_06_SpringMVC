package com.callor.jdbc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value="/author")
public class AuthorController {

	// 링크를 타고 들어오는 것들?
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert() {
		return "author/input";
	}
}
