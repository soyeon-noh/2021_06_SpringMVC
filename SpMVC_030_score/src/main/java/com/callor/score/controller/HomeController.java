package com.callor.score.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.callor.score.model.StudentVO;
import com.callor.score.service.StudentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Controller
public class HomeController {

	protected final StudentService stService;

//	@ResponseBody
	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public Map<String, Object> home(Locale locale, Model model) {
	public String home(Locale locale, Model model) {

		List<StudentVO> stList = stService.selectAll();// 원래 List를 받아서
		Map<String, Object> maps = stService.selectMaps(); 
		log.debug("Controller {}", stList.toString()); // String으로 변환해서 넣어줘야했는데 그럴필요없어졌대
//		return maps;
		return "home";
	}
	
	//뭐든 return에 넣으면... 데이타를 보여준대 (jackson-databind)

	/*
	 * @ResponseBody 
	 * API Server 를 만들때 String type의 데이터를 Response 하라 는 지시어
	 * 
	 * API Server 
	 * 서로 다른 서버와 서버, 서버와 클라이언트 간의 조건을 Request하고, 
	 * 그 결과를 Data로 Response하는 서비스
	 * 
	 * 서로다른 프로젝트로 서버와 클라이언트를 개발한다. 
	 * API Service는 주고받는 데이터를 XML type, JSON type으로 만든다.
	 * 
	 */

	/* @RequestMApping (일반적인 요청과 응답)
	 * 사용자가 home이라는 url로 요청하면 view파일에있는 home.jsp를 보내달라
	 */
	@ResponseBody
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String string(
			@RequestParam(name="name", required = false, defaultValue = "") 
			String name) {
		
		if(name != null && name.equals("HOME")) {
			return "OK";
		}else {
			return "FAIL";
		}
	}
	
	// API는 view가 아니라 문자열을 주고받는거다
	// 근데요즘엔 XML이나 JSON type을 주고받는대

}
