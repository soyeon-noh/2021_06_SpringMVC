package com.callor.jdbc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.callor.jdbc.model.CompVO;
import com.callor.jdbc.pesistance.CompDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j // 로그를 보기 위해서
@Controller
@RequestMapping(value="/comp")
public class CompController {

	protected final CompDao compDao;
	public CompController(CompDao compDao) {
		this.compDao = compDao;
	}
	
	// localhost:8080/jdbc/comp/insert 로 호출되는 함수
	@RequestMapping(value="/insert", method=RequestMethod.GET) // insert GET method
	public String insert() {
		
		// WEB-INF/views/comp/input.jsp 를 열어라
		return "comp/input";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST) // insert POST method
	public String insert(CompVO cmVO) { // 입력받은걸 직접 VO로 넘겨받을 수 있다?
		
		log.debug("Company VO {}", cmVO.toString());
		compDao.insert(cmVO); // insert method에 cmVO를 전달해줌
		
		return "redirect:/"; // rootPath로 redirect를 하라
	}
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update() {
		
		return "comp/update";
	}
}
